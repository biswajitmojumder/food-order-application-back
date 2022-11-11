# Food Ordering and Delivery - Backend ReadMe
## About
This is the backend of the Food Ordering and Delivery Application
Frontend link: https://github.com/survivor97/food-order-application-front

## Application Diagram
<img src="/img/activity_diagram.png">

## Endpoints

<h3>GET:</h3>

    /token/refresh_token                                                    - if the refresh token from the Authorization header is valid, a new access token is returned
    /image/get?name={resourceName}                                          - if the image with the resourceName exists, returns the image
    /orders/all                                                             - returns all the orders from the server
    /orders/accepted                                                        - returns all the accepted orders (which does not have the status RECEIVED or REJECTED)
    /orders?status={OrderStatus}                                            - returns all orders that have the OrderStatus. OrderStatus = { RECEIVED, ACCEPTED, PREPARING, PICK_READY, ON_THE_WAY, DELIVERED, REJECTED }    
    /food/get?id={foodId}                                                   - returns the food with foodId
    /food?page={pageNr}                                                     - returns a page of food with 9 entries
    /food/search?category={category}&restaurantName={name}&page={pageNr}    - search food by category and name containing the requested parameters; Categories = { FAST_FOOD, BURGER, DESSERT, DRINK, GRILL, PIZZA }
    /food/{category}?page={pageNr}                                          - returns a page of food of category with 9 entries
    /food/{category}/{restaurantId}?page={pageNr}                           - returns a page of food of category from restaurant with restaurantId with 9 entries
    /restaurant/get-all                                                     - returns all restaurants
    /restaurant/get?id={restaurantId}                                       - return the restaurant with restaurantId
    /admin/get-admin-info                                                   - return the info for the authenticated admin account
    /manager/get-all                                                        - return all managers    
    /manager/get-manager-info                                               - return the info for the authenticated manager account
    /user/get-user-info                                                     - return the info for the authenticated user (customer) account
    /user/get-all                                                           - return all users
    /user/get-all?page={pageNr}                                             - return a page of users with 9 entries
    /user/search?username={username}&email={email}                          - search an user by username and email
    /user/get-favourite-food                                                - return the favourite food list for the authenticated user
    /user/get-order-history                                                 - return the order history list for the authenticated user
    /staff/get-all                                                          - return all staff accounts
    /staff/get-staff-info                                                   - return the info for the authenticated staff
    /delivery-user/get-all                                                  - return all delivery user accounts
    /delivery-user/get-delivery-user-info                                   - return the info for the authenticated delivery user account
    /delivery-user/get-order-history                                        - return the order history for the authenticated delivery user
    /delivery-user/active-order                                             - return the active order for the authenticated delivery user
---
<h3>POST:</h3>

    /login                              - On success, returns { access_token, refresh_token }
    {
        "username": "user",
        "password": "password"
    }

    /image/upload                       - On success, returns { message, image: { id, name, type, resourceName } }
    {
        "image": multipart image file
    }

    /orders/new-authenticated           - On success, returns the order object; Inserts a new order for the authenticated user    
    {
        "foodList": 
        [
            {
                "id": 1 -> food Id
            },
            {
                "id": 2
            }
        ],
        "address": 
        {
            "streetAddress": "Address",
            "city": "City",
            "zipCode": "123456"
        }
    }

    /food/insert                        - On success, returns the food object
    {
        "name": "name",
        "description": "description",
        "price": "price",
        "weight": "weight",
        "foodCategory": 
        {
            "name": "PIZZA"
        },
        "restaurantList":
        [
            {
                "id": 1
            }
        ]
    }

    /restaurant/new                     - On success, returns the restaurant
    {
      "name": "Restaurant 1",
      "description": "Main restaurant",
      "address": 
      {
        "streetAddress": "Address",
        "city": "City",
        "zipCode": "123456"
      }
    }

    /manager/new                        - On success, returns the manager
    {
        "firstName": "Phil",
        "lastName": "Collen",
        "email": "email@email.com",
        "username": "username",
        "password": "password"
    }

    /user/new                           - On success, returns the user
    {
        "firstName": "John",
        "lastName": "Sykes",
        "phoneNumber": "0712345678",
        "email": "email@email.com",
        "username": "username",
        "password": "password"
    }

    /staff/new                          - On success, returns the staff
    {
        "firstName": "Ronnie",
        "lastName": "James",
        "email": "email@email.com",
        "username": "dio",
        "password": "password"
    }

    /delivery-user/new                  - On success, returns the delivery user
    {
        "firstName": "Dimebag",
        "lastName": "Darrell",
        "email": "email@email.com",
        "username": "username",
        "password": "password",
        "vehicleManufacturer": "BMW",
        "vehicleNumber": "B-01-DMB",
        "vehicleColor": "BLUE",
        "phoneNumber": "0712345678"
    }
---
<h3>PUT:</h3>

    /orders/accept-order?id={orderId}           - Accept the order
    /orders/prepare-order?id={orderId}          - Set the order pick-ready
    /orders/on-the-way?id={orderId}             - Set the order on-the-way
    /orders/set-delivered-status?id={orderId}   - Set the order delivered
    /orders/set-rejected-status?id={orderId}    - Set rejected status

    /food/update                                - Update a food
    {
        "id": 1,
        "name": "pizza",
        "description": "the best pizza in town",
        "price": "40",
        "weight": "500",
        "foodCategory": 
        {
            "name": "PIZZA"
        },
        "restaurantList": 
        [
            {
                "id": 1
            }
        ]
    }

    /food/set-restaurant/{foodId}/{restaurantId}    - Set the restaurant with restaurantId for the food with foodId
    /food/set-image/{foodId}?uuid={resourceName}    - Set an image for the food with foodId

    /restaurant/update                              - Update a restaurant with specific id
    {
        "id": 1,
        "name": "Restaurant 1",
        "description": "Main restaurant",
        "address": 
        {
            "streetAddress": "Address",
            "city": "City",
            "zipCode": "123456"
        }
    }

    /admin/update                                   - Update the authenticated admin account
    {
        "firstName": "Phil",
        "lastName": "Collen",
        "email": "email@email.com",
        "password": "password"
    }

    /manager/update                                 - Update a manager account with specific id
    {
        "id": 15
        "firstName": "Vivian",
        "lastName": "Campbell",
        "email": "email@email.com",
        "password": "password"
    }

    /manager/update-authenticated                   - Update the authenticated manager account
    {
        "firstName": "Vivian",
        "lastName": "Campbell",
        "email": "email@email.com",
        "password": "password"
    }

    /user/update                                    - Update the authenticated user account
    {
        "firstName": "John",
        "lastName": "Sykes",
        "phoneNumber": "0712345678",
        "email": "email@email.com",
        "password": "password"
    }

    /staff/update                                   - Update a staff with specific id
    {
        "id": 19,
        "firstName": "Steve",
        "lastName": "Clark",
        "email": "email@email.com",
        "password": "password"
    }

    /staff/update-authenticated                     - Update the authenticated staff account
    {
        "firstName": "Steve",
        "lastName": "Clark",
        "email": "email@email.com",
        "password": "password"
    }

    /delivery-user/update                           - Update a delivery user with specific id
    {
        "id": 21
        "firstName": "Davil",
        "lastName": "Coverdale",
        "email": "email@email.com",
        "password": "password",
        "vehicleManufacturer": "BMW",
        "vehicleNumber": "B-01-DMB",
        "vehicleColor": "BLUE",
        "phoneNumber": "0712345678"
    }

    /delivery-user/update-authenticated             - Update the authenticated delivery user
    {
        "id": 21
        "firstName": "Davil",
        "lastName": "Coverdale",
        "email": "email@email.com",
        "password": "password",
        "vehicleManufacturer": "BMW",
        "vehicleNumber": "B-01-DMB",
        "vehicleColor": "BLUE",
        "phoneNumber": "0712345678"
    }
---
<h3>DELETE:</h3>

    /food/delete?id={foodId}                                - Delete food
    /restaurant/delete?id={restaurantId}                    - Delete restaurant    
    /manager/delete?id={managerId}                          - Delete manager
    /user/delete?id={userId}                                - Delete user
    /user/remove-food-from-favourites?id={foodId}           - Remove food from authenticated user's favourite food list
    /staff/delete?id={staffId}                              - Delete staff
    /delivery-user/delete?id={deliveryUserId}               - Delete delivery user

---
       --- Premade Accounts: ---
       username: admin, user_1, ..., user_10, staff_1, delivery_user_1, manager_1
       password: password