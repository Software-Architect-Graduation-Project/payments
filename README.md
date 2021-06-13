# payments
Project to process eccomerce payments

docker run -d \
    --name payments-db \
    -p 5432:5432 \
    -e POSTGRES_PASSWORD=postgres \
    -v $(pwd)/docker/setup-db.sh:/docker-entrypoint-initdb.d/setup-db.sh \
    postgres:13.3

docker exec -it broker /bin/kafka-console-producer --broker-list localhost:9092 --topic payment_received

{ "clientId" : "123", "orderId" : "456", "paymentPlan" : "10x" }