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
  - List by default at runtime, isn't aware of custom object, at runtime it compiles with top level iterator ``Object``
  - Therefore, advised to use array as we can set custom type during compile time itself