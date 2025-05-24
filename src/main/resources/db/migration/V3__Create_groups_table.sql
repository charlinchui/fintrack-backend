CREATE TABLE groups (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_by UUID NOT NULL,
    created_at TIMESTAMP NOT NULL,
    
    CONSTRAINT fk_groups_created_by FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE CASCADE
);

-- User-Group relationship table (many-to-many)
CREATE TABLE user_groups (
    user_id UUID NOT NULL,
    group_id UUID NOT NULL,
    joined_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    role VARCHAR(50) NOT NULL DEFAULT 'MEMBER', -- ADMIN, MEMBER
    
    PRIMARY KEY (user_id, group_id),
    
    CONSTRAINT fk_user_groups_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_user_groups_group FOREIGN KEY (group_id) REFERENCES groups(id) ON DELETE CASCADE
);

-- Add foreign key constraint to categories table for group_id
ALTER TABLE categories 
ADD CONSTRAINT fk_categories_group 
FOREIGN KEY (group_id) REFERENCES groups(id) ON DELETE CASCADE;

-- Indexes
CREATE INDEX idx_groups_created_by ON groups(created_by);
CREATE INDEX idx_user_groups_user_id ON user_groups(user_id);
CREATE INDEX idx_user_groups_group_id ON user_groups(group_id);

-- Comments
COMMENT ON TABLE groups IS 'Groups that users can join to share expenses and categories';
COMMENT ON TABLE user_groups IS 'Many-to-many relationship between users and groups';
COMMENT ON COLUMN user_groups.role IS 'User role in the group: ADMIN or MEMBER';

