# exercise-microservice-mq

## Setup

Build the containers

```bash
    docker-compose build
```

Run RabbitMQ

```bash
    docker-compose up -d rabbitmq
```

Run the sender (service-a)

```bash
    docker-compose up -d service-a
```

Run the receiver (service-b)

```bash
    docker-compose up service-b
```

## Sending messages

Attatch to `service-a` containter and run the sender

```bash
    java Send
```

Type your name...

## Cleanup

```bash
    docker-compose down
```