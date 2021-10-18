
/*
        Name,   Address,                                 Address Type,  Tax Amount,     Biography
        Walt    410 Scott Drive Pittsburgh, PA 15237      Primary            3.0        Instructor at Tech Elevator
        Oliver  410 Scott Drive Pittsburgh, PA 15237      Secondary          3.0        Cat that interrupts class
        Taylor   55 House Road New York, New York 10001   Primary            4.0        Aspiring comedian


  Address Type Id               Address Type
  1                             Primary
  2                             Secondary

  Person_Id     Address_Id         Address Type  
   1                1                1                 
   2                1                2            
   3                2                1                   
   2                3                1               
   
   Person_Id    Name      Biography
   1            Walt      Instructor at Tech Elevator
   2            Oliver    Cat that interrupts class
   3            Taylor    Aspiring comedian
   
   Address_Id   House #         Street          City Id         
   1            410             Scott Drive        1 
   2            55              House Road         2     
   3            410             Outside            1       
   
   State        Tax Amount
   PA           3.0
   NY           4.0
   
   City Id      Name            State   
   1            Pittsburgh      PA      
   2            New York        NY      
   
*/

DROP TABLE IF EXISTS purchase_painting;
DROP TABLE IF EXISTS purchase;
DROP TABLE IF EXISTS art;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS artist;

CREATE TABLE artist
(
        artist_id SERIAL PRIMARY KEY,
        name CHARACTER VARYING(50) NOT NULL
);


CREATE TABLE customer
(
        customer_id SERIAL PRIMARY KEY,
        name CHARACTER VARYING(50) NOT NULL,
        address CHARACTER VARYING(250),
        phone CHARACTER VARYING(10)
);


CREATE TABLE art
(
        art_id SERIAL PRIMARY KEY,
        name CHARACTER VARYING(100) NOT NULL,
        artist_id INTEGER REFERENCES artist (artist_id)
);

CREATE TABLE purchase
(
        purchase_id SERIAL,
        date TIMESTAMP NOT NULL DEFAULT(CURRENT_TIMESTAMP),
        customer_id INTEGER,
        
        CONSTRAINT pk_purchase_id PRIMARY KEY (purchase_id),
        CONSTRAINT fk_customer_id FOREIGN KEY (customer_id) REFERENCES customer (customer_id)
);

CREATE TABLE purchase_painting
(
        purchase_id INTEGER,
        art_id INTEGER,
        sales_price MONEY,
        
        CONSTRAINT pk_purchase_id_art_id PRIMARY KEY (purchase_id, art_id),
        CONSTRAINT fk_purchase_id FOREIGN KEY (purchase_id) REFERENCES purchase (purchase_id),
        CONSTRAINT fk_art_id FOREIGN KEY (art_id) REFERENCES art (art_id),
        CONSTRAINT chk_sales_prices CHECK (sales_price >= '0')
        
);

-- INSERT DATA INTO TABLE HERE





/*
        Group 1         Reshmi et al
        
        Artist
        artist_id (PK)       name
        
        Art
        art_id  (PK)        name            artist_id (FK)
        
        Purchase
        purchase_id (PK)        date    art_id (FK)     sales_price        customer_id (FK)
        
        Customer
        customer_id (PK)     name    address         phone
        
        
        Group 2         Chris et al
        
        Artist
        artist_id (PK)      name
        
        Artwork
        artwork_id (PK)     artist_id (FK)      title
        
        Customer
        customer_id  (PK)   name    address          phone          city            province                postal_code
        
        Purchase
        purchase_id  (PK)   customer_id (FK)    price   date    time    artwork_id (FK)
        
        
        Group 3         Josh et al
        
        Purchase
        purchase_id     purchase_date  customer_id
        
        Purchase_Painting
        purchase_id     artwork_id  (PK)        sales_price
        
        
        Group 4       Nick et al  

        painting
        painting_id     artist_id  (FK to person)      art_title

        purchase   
        purchase_id     painting_id     customer_id (FK to person)    date    price
        
        person
        person_id       name    address         phone
        


*/
