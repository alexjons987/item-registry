INSERT INTO roles (id, name)
    VALUES  (1, 'ADMIN'),
            (2, 'USER')
    ON DUPLICATE KEY UPDATE id = id;

INSERT INTO achievements (id, name, description)
    VALUES  (1, "My Precious", "Obtain your first item."),
            (2, "Legen... Wait for it... -dary!", "Acquire your first Legendary item."),
            (3, "A conversation piece", "Acquire your first Mythic item."),
            (4, "It's Over 9000!", "Acquire an inventory value of 9000 or more.")
    ON DUPLICATE KEY UPDATE id = id;
