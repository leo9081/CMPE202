# CMPE202Starbucks
## StarBucks Architecute
### Design Decisions
Our team decided to implement a real Android App and use Processing simulator to demenstate the project.

### Architecute Diagram
![Copy of ROM Angle Calculation flow](https://user-images.githubusercontent.com/15006828/57574384-ee5c1900-73ec-11e9-8c1e-dab2ba56cf00.png)
### UI WireFrames
![App Simple design](https://user-images.githubusercontent.com/15006828/57574374-ce2c5a00-73ec-11e9-9421-019e59958a3a.png)

## Feature Sets of the Projece
### Introduction
With Starbucks Android application, users are able to order Starbucks coffees through their smartphones. Using this application enable users to go through the order process earsier, more convenient, more private, faster and effortless. 

By using this application, user will create an account for authentification so that they can access the information in the account -- authorization. This authentification and authorization ensure the privacy of users. Users can add/save their payment methods, such as debit cards, credit cards inside their accound for making the payments of their oders. Users can also manage their orders on their phones, they can add, delete orders, make payment of the orders, etc.

The whold ordering process can be made on phone, which save the users waite time in store. Users can simply make orders on their phone and go pick up the orders in the nearest store. 

### Vision
* Product Vision

   Initially we want to focus on making the order process easier for cusomers. Utimately, we want every customer -- existing one or potential one to be able to connect themseves with our stores, Feel belonging to the products, and making desicions on orders easier.
* Design Vision

     Refer to Design Decision Section. 
   In the future, we would carry our application further with more interactive user interface such as more graphic to represent the store and the product. More user friendly interfaces. 
* Business Vision

   With the starbucks mobile application, the process of making order is faster and more convenient. Users will be more willing to make a purchase for store since there is less wait time and less mistakes. With those benefits, it is more likely to introduce new customers for the business. 

### Product
* Information Architecture

   Refer to UI WireFrames digram.

* Technical Architecture

   Refer to UI Architecture digram.
   
  * List of APIs:
    * User login API
    * Add Cards API
    * Managed Orders API
    * Payment API
    
* Features
  - User login API
  
  With the user login API, user can create an account. Once the account is created, user can login his/her account welcome page. In the welcome page, user will see his/her account information, which includes card number, card balance, and code. In this welcome page, user can click the red buttom to add a card, click the payment button to make a payment, and can click oder history to show/review all the orders that the users have made before.
  
  - Add Cards API
  
  Once the user is in the add Cards page, the user can add a card with the card number and card code.
  
  - Managed Orders API
  
  When the user click the payment history, user will be directed to the managed orders API, in this API, user can view all the orders history. With this history, user can make sure every payment is correct, which product he/she prefer, and if the payment got charged is correct or not.
  
  - Payment API
* Product Roadmap
  - Connect with Others
  
     One of the possible next implementation for our applciation will be allowing users to comment on products of the stores. They can also talk to poeple who share the same interest/taste of the product. We could therefore build a social API on top of the application to allow users to connet. 
  - Connect with the Stores
  
    Users can also giving feedback or advise to the stores about their products, intererior design or customer service etc.
  - Web Integration
  
    Users can access their account online to manage their acount and also perform the process of ordering. They can also be able to share their profile or partial profile if they like to.

Google Sheet:
https://drive.google.com/open?id=1cPv0kQO1KPXDTU8JFu51ucNCyTiYmkPy
