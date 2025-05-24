CREATE TABLE users (
  id UUID PRIMARY KEY,
  auth0_id VARCHAR(255) NOT NULL UNIQUE,
  email VARCHAR(255) NOT NULL UNIQUE,
  name VARCHAR(255) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  last_login TIMESTAMP NOT NULL
);

-- Indexes
CREATE INDEX idx_users_auth0_id ON users(auth0_id);
CREATE INDEX idx_users_email ON users(email);

-- Comments
COMMENT ON TABLE users IS 'Application users synchronized from Auth0';
COMMENT ON COLUMN users.auth0_id IS 'Auth0 user identifier from JWT sub claim';
COMMENT ON COLUMN users.email IS 'User email address from Auth0';
COMMENT ON COLUMN users.name IS 'User display name from Auth0';
COMMENT ON COLUMN users.created_at IS 'When the user was first created in our system';
COMMENT ON COLUMN users.last_login IS 'Last time user logged in (updated on each login)';
