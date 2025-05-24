CREATE USER fintrack WITH PASSWORD 'secret';

CREATE DATABASE fintrack_dev OWNER fintrack;
CREATE DATABASE fintrack_test OWNER fintrack;

GRANT ALL PRIVILEGES ON DATABASE fintrack_dev TO fintrack;
GRANT ALL PRIVILEGES ON DATABASE fintrack_test TO fintrack;
