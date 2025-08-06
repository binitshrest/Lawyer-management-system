-- Insert query for user table
INSERT INTO tbl_user (first_name, last_name, email, password, role_id, created_at, update_at)
VALUES
    ('Alice', 'Anderson', 'alice@example.com', 'encryptedpass1', 1, now(), now()),
    ('Bob', 'Brown', 'bob@example.com', 'encryptedpass2', 2, now(), now()),
    ('Carol', 'Clark', 'carol@example.com', 'encryptedpass3', 1, now(), now()),
    ('David', 'Doe', 'david@example.com', 'encryptedpass4', 3, now(), now()),
    ('Eva', 'Evans', 'eva@example.com', 'encryptedpass5', 2, now(), now());


-- Insert corresponding lawyer-specific data
INSERT INTO tbl_lawyer (id, licence_number, specialization, years_of_experience, availability_status, hourly_rate, is_verified)
VALUES
    (1, 'LN123456', 'Family Law', '5', 'ONLINE', 50.0, true),
    (2, 'LN789012', 'Criminal Law', '8', 'OFFLINE', 75.0, false);

-- Insert dummy availability slots for lawyers

INSERT INTO tbl_availability (lawyer_id, start_time, end_time, is_booked)
VALUES
    -- Lawyer 1: Bob Brown
    (1, '2025-08-06 10:00:00', '2025-08-06 11:00:00', false),
    (1, '2025-08-06 14:00:00', '2025-08-06 15:00:00', true),

    -- Lawyer 2: David Doe
    (2, '2025-08-07 09:00:00', '2025-08-07 10:00:00', false),
    (2, '2025-08-07 16:00:00', '2025-08-07 17:00:00', false);

INSERT INTO tbl_appointment (
    user_id,
    lawyer_id,
    scheduled_date,
    scheduled_time,
    duration_minutes,
    status
) VALUES
      (1, 1, '2025-08-10', '2025-08-10 10:00:00', 30, 'CONFIRMED'),
      (2, 2, '2025-08-13', '2025-08-13 11:15:00', 30, 'COMPLETED');