CREATE TABLE public."payment" (
    id serial NOT NULL,
    client_id varchar NOT NULL,
    order_id varchar NOT NULL,
    payment_plan varchar NOT NULL,
    created_on timestamp(0) NOT NULL DEFAULT now(),
    PRIMARY KEY (id)
);