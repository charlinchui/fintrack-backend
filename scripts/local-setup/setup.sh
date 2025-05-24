#!/bin/bash

echo "🚀 Setting up Fintrack local development environment..."

# Check if Docker is running
if ! docker info > /dev/null 2>&1; then
    echo "❌ Docker is not running. Please start Docker first."
    exit 1
fi

# Navigate to docker directory
cd "$(dirname "$0")/../docker"

echo "🛑 Stopping any existing containers..."
docker-compose down

# Start the database
echo "📦 Starting PostgreSQL database..."
docker-compose up --build -d

# Function to check if PostgreSQL is ready
check_postgres() {
    docker exec fintrack-postgres pg_isready -U postgres > /dev/null 2>&1
}
# Wait for database to be ready
echo "⏳ Waiting for database to be ready..."
TIMEOUT=60
COUNTER=0

while ! check_postgres; do
    if [ $COUNTER -ge $TIMEOUT ]; then
        echo "❌ Database failed to start within $TIMEOUT seconds"
        echo "📋 Container logs:"
        docker logs fintrack-postgres --tail 20
        exit 1
    fi
    
    echo "   Still waiting... ($COUNTER/$TIMEOUT seconds)"
    sleep 2
    COUNTER=$((COUNTER + 2))
done

echo "✅ Database is ready!"
echo "🔗 Database connection details:"
echo "   Host: localhost:5432"
echo "   Database: fintrack_dev"
echo "   Username: fintrack_user"
echo "   Password: fintrack_password"
echo ""
echo "🏃 You can now run your Spring Boot application:"
echo "   ./mvnw spring-boot:run"
