CREATE TABLE categories (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    owner_id UUID NOT NULL,
    group_id UUID,
    created_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_categories_owner FOREIGN KEY (owner_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Indexes
CREATE INDEX idx_categories_owner_id ON categories(owner_id);
CREATE INDEX idx_categories_group_id ON categories(group_id);

-- Unique constraint: category name must be unique per owner/group combination
CREATE UNIQUE INDEX idx_categories_unique_personal 
    ON categories(name, owner_id) 
    WHERE group_id IS NULL;

CREATE UNIQUE INDEX idx_categories_unique_group 
    ON categories(name, group_id) 
    WHERE group_id IS NOT NULL;

-- Comments
COMMENT ON TABLE categories IS 'Expense categories - can be personal or group-owned';
COMMENT ON COLUMN categories.name IS 'Category name (e.g., "Food", "Transportation")';
COMMENT ON COLUMN categories.description IS 'Optional description of the category';
COMMENT ON COLUMN categories.owner_id IS 'User who created this category';
COMMENT ON COLUMN categories.group_id IS 'Group this category belongs to (NULL for personal categories)';

