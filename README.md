
# 📧 Email Microservice for E-Commerce System

[![Java](https://img.shields.io/badge/Java-17%2B-orange?logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1%2B-brightgreen?logo=spring)](https://spring.io/)
[![Apache Kafka](https://img.shields.io/badge/Apache%20Kafka-3.0%2B-black?logo=apachekafka)](https://kafka.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-blue)](LICENSE)

A dedicated email notification service that integrates with the [E-Commerce FakeStore API System](https://github.com/ChaitanyaNadagouda/E-Commerce_fakestoreinteract) via Apache Kafka to handle transactional emails asynchronously.

## 🌟 Key Features
- **Kafka Consumer** - Listens to `send_email` topic for email requests
- **SMTP Integration** - Configurable email delivery via Gmail SMTP
- **Template Support** - Pre-built templates for order confirmations and notifications
- **Retry Mechanism** - Automatic retry for failed email deliveries
- **Async Processing** - Non-blocking email processing for better performance

## 📌 Table of Contents
- [Architecture](#-architecture)
- [Integration](#-with-e-commerce-service)
- [Setup](#-setup-guide)
- [Configuration](#-configuration)
- [API Reference](#-api-reference)
- [Local Development](#-local-development)
- [Troubleshooting](#-troubleshooting)


### 🔄Integration with E-Commerce Service
This service works in tandem with the E-Commerce Service:

#### Event Flow:

E-Commerce service places order → publishes Kafka event

Email service consumes event → sends confirmation email


Message Format:

json
{
  "to": "customer@example.com",
  "subject": "Order Confirmation #12345",
  "body": "Thank you for your order!...",
  "isHtml": true
}

#### 🛠 Setup Guide

## Prerequisites
Java 17+

Apache Kafka running locally

Gmail account with App Password enabled

## Installation
Clone the repository:

git clone https://github.com/ChaitanyaNadagouda/emailService.git
cd emailService


## Configure environment variables:

- export SPRING_MAIL_USERNAME=your@gmail.com
- export SPRING_MAIL_PASSWORD=your-app-password
- export KAFKA_BOOTSTRAP_SERVERS=localhost:9092

## Build and run:

mvn clean package

java -jar target/email-service-0.0.1-SNAPSHOT.jar

#### 🤝 Contributing
Pull requests are welcome! Please ensure:

Tests are updated

Kafka message schema remains backward compatible
