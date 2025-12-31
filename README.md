📘 Smart Commute – Urban Intelligent Commuting Backend System

A city-level intelligent commuting backend system built with Spring Boot + MyBatis + Redis + JWT.

This project implements core backend capabilities for urban commuting scenarios, including user authentication, route planning, third-party map integration, order & payment processing, API rate limiting, and cache optimization, with a focus on high concurrency and system reliability.

🚀 Project Highlights
⭐ 1. Distributed Authentication & Authorization (JWT + Spring Security)

Custom JwtFilter for token validation and parsing

Spring Security with RBAC (Role-Based Access Control)

Stateless authentication (no session), naturally scalable for distributed systems

⭐ 2. Route Planning Service (OpenStreetMap API)

Dynamic route calculation via OpenStreetMap (OSM) API

Route results cached in Redis to avoid redundant third-party API calls

Optimized for high-frequency route queries

⭐ 3. Order & Payment Module (with Callback & Idempotency)

Complete payment flow:

Order creation → payment request → async callback → transaction logging

Redis-based idempotency control for payment callbacks

MySQL persistence for orders and payment logs

Order state machine:

CREATED → PAYING → SUCCESS / FAILED

⭐ 4. API Rate Limiting with Redis + Lua (High-Concurrency Friendly)

IP-based and endpoint-level rate limiting

Lua scripts ensure atomic operations and thread safety

Protects high-frequency APIs (especially route planning) from abuse

⭐ 5. Redis + MySQL Dual-Level Cache Architecture

Hot route data cached in Redis

Protection against:

Cache penetration

Cache breakdown

Cache avalanche (randomized TTL)

MySQL as the final source of truth (eventual consistency)

🛠️ Tech Stack
Category	Technology
Backend Framework	Spring Boot
Authentication	Spring Security + JWT
ORM	MyBatis
Database	MySQL
Cache	Redis
Concurrency Control	Redis + Lua
Map API	OpenStreetMap
Build Tool	Maven

📌 Core Features
🔐 1. Authentication (JWT)

JWT issued upon successful login

JwtFilter intercepts requests and extracts user identity

Integrated with Spring Security for role-based access control

🗺️ 2. Route Planning (OpenStreetMap)

Example request:

GET /route?start=39.9,116.3&end=39.8,116.4


Includes:

OSM API integration

Redis-based route caching

💳 3. Order & Payment Module (Key Feature)

Order lifecycle:

Create Order → Pay Request → Status Update → Async Callback → Payment Log Persistence


Includes:

Payment idempotency via Redis SETNX

Persistent transaction logs (PaymentLog)

Order state machine:

CREATED → PAYING → SUCCESS

🛡️ 4. API Rate Limiting (Redis + Lua)

Per-IP and per-endpoint rate limits

High-frequency endpoints protected with rate limiting

Lua scripts guarantee atomicity and concurrency safety

🧠 5. Dual-Level Cache Architecture

Read-through cache with Redis as first layer

Fallback to MySQL on cache miss, followed by cache rebuild

Randomized TTL to mitigate cache avalanche

✅ Suitable For

Backend system design interviews

High-concurrency web service practice

Distributed system fundamentals (auth, cache, idempotency, rate limiting)
