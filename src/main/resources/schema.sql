CREATE TABLE PRICES (
    brand_id INT NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    price_list INT NOT NULL,
    product_id INT NOT NULL,
    priority INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    curr VARCHAR(3) NOT NULL,
    PRIMARY KEY (brand_id, start_date, end_date, price_list, product_id)
);