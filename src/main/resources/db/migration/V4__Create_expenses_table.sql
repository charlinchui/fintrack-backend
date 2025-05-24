CREATE TABLE expenses (
    id UUID PRIMARY KEY,
    description VARCHAR(500) NOT NULL,
    amount DECIMAL(12, 2) NOT NULL CHECK (amount > 0),
    category_id UUID NOT NULL,
    user_id UUID NOT NULL,
    group_id UUID, -- NULL for personal expenses
    expense_date TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    
    CONSTRAINT fk_expenses_category FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE RESTRICT,
    CONSTRAINT fk_expenses_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_expenses_group FOREIGN KEY (group_id) REFERENCES groups(id) ON DELETE CASCADE
);

-- Indexes
CREATE INDEX idx_expenses_user_id ON expenses(user_id);
CREATE INDEX idx_expenses_group_id ON expenses(group_id);
CREATE INDEX idx_expenses_category_id ON expenses(category_id);
CREATE INDEX idx_expenses_expense_date ON expenses(expense_date);
CREATE INDEX idx_expenses_user_date ON expenses(user_id, expense_date);
CREATE INDEX idx_expenses_group_date ON expenses(group_id, expense_date);

-- Comments
COMMENT ON TABLE expenses IS 'Individual expense records';
COMMENT ON COLUMN expenses.description IS 'What the expense was for';
COMMENT ON COLUMN expenses.amount IS 'Expense amount (always positive)';
COMMENT ON COLUMN expenses.expense_date IS 'When the expense occurred';
COMMENT ON COLUMN expenses.group_id IS 'Group this expense belongs to (NULL for personal expenses)';

