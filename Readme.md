### Product Service Inventory Fetch using FakeStore Document 

------------

The Design of service goes as per the latest spring MVC pattern 

- models : All the table related info
- controllers : first point of contact for client 
- dtos : interacting with databases
- configs : storing any custom class configs 
- services : handling business logic 


-----

#### Product Service Implementation

- Its been implemented using **REST** based template
- Utilizing the [FakeStore](https://fakestoreapi.com/docs) doc data


#### Class level details 

- Services : ProductService 
  - ProductService interface has method `` getAllProducts()`` whose impl is done using ``FakeStoreProductDto`` array instead of List
  - If we use, response type of dto as `List<FakeStoreProductDto>.class` its gives error _cannot access object of parameterized type_.
  - if we use directly, `List.class`,
  - List by default at runtime, isn't aware of custom object, at runtime it compiles with top level iterator ``Object``, concept known as **Type Eraser**
  - **Type Eraser** was introduced to have back word compatible before Java5 
  - Therefore, advised to use array as we can set custom type during compile time itself, because with List deserialization is not possible at runtime.
  - JSON array, is not same as List<> of array.


- Controller : `getProductById(id)`
  - In order to handle the exception, for Product Not found, the below `ResponseEntity<>` returns compile time error 
  - error : `Requried String, Provided ResponseEntity<Product>`, therefore we have to create a exceptional handler 
  - ```java
    try{
        product = productService.getProductById(id);
        productResponseEntity = new ResponseEntity<>(product, HttpStatus.OK);
      }catch (InstanceNotFoundException exception){
            productResponseEntiry = new ResponseEntity<>("Product Not found with id " + id, HttpStatus.NOT_FOUND); -> error
      }
    ```
  - We can fix this, by adding `@ExceptionHandler(CustomException.class)` at controller level & at service level we can add
  - ```java
    if( fakeStoreProductDto == null ){
    //productResponseEntity = new ResponseEntity<Product>("Product Not found", HttpStatus.NOT_FOUND);
     throw new InstanceNotFoundException("Product Not Found with id" + id);
    }
    ```
  - On throwing the custom exception, we have add a filter to handle any exception, `@ExceptionHanlder(customException.class)` is used to handle it.
  - ```java
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
      Product product = productService.getProductById(id);
      ResponseEntity<Product> productResponseEntity = new ResponseEntity<>(product, HttpStatus.OK);
      return productResponseEntity;
    }
    ```
  - Therefore, the custom Exception handler is as below
  - ```json
    {
      "errorCode": 100,
      "message": "Product Not Found with id21"
    }
   ```

- In order to handle exceptions in better way, spring have `@ControllerAdvice` annotation, which acts as filter between client - controller
- `@ControllerAdvice` does final check on whatever being returned by controller, its at global level.
