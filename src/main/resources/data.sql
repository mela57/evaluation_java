-- Insertion des utilisateurs
INSERT INTO users (username, email, password, first_name, last_name, created_at) VALUES
('admin', 'admin@admin.com', 'Azerty23!', 'Admin', 'User', NOW()),
('Laura', 'Laura.gautier@user.com', 'Azerty23!', 'Laura', 'gautier', NOW()),
('Greg', 'Greg.wolff@user.com', 'Azerty23!', 'Greg', 'wolff', NOW()),
('Flo', 'Flo.roques@user.com', 'Azerty23!', 'Flo', 'roques', NOW());

-- Insertion des tâches
INSERT INTO tasks (title, description, status, priority, user_id, created_at, updated_at, due_date) VALUES
('Configurer SpringBoot', 'Mettre en place l''application SpringBoot avec les dépendances nécessaires', 'DONE', 'HIGH', 1, NOW(), NOW(), DATEADD('DAY', 1, NOW())),
('Créer les modèles', 'Implémenter les entités User et Task avec les relations JPA', 'DONE', 'HIGH', 1, NOW(), NOW(), DATEADD('DAY', 2, NOW())),
('Développer les controllers', 'Créer les controllers REST et Web pour gérer les utilisateurs et tâches', 'IN_PROGRESS', 'MEDIUM', 1, NOW(), NOW(), DATEADD('DAY', 3, NOW())),
('Créer les templates', 'Développer l''interface utilisateur avec Thymeleaf', 'TODO', 'MEDIUM', 1, NOW(), NOW(), DATEADD('DAY', 5, NOW())),
('Tests unitaires', 'Écrire les tests pour valider le fonctionnement', 'TODO', 'LOW', 1, NOW(), NOW(), DATEADD('DAY', 7, NOW())),

('Apprendre Spring', 'Étudier la documentation de Spring Framework', 'IN_PROGRESS', 'MEDIUM', 2, NOW(), NOW(), DATEADD('DAY', 10, NOW())),
('Projet personnel', 'Développer une application web personnelle', 'TODO', 'LOW', 2, NOW(), NOW(), DATEADD('DAY', 15, NOW())),
('Révisions examens', 'Préparer les examens de fin de semestre', 'TODO', 'HIGH', 2, NOW(), NOW(), DATEADD('DAY', 20, NOW())),

('Organiser réunion équipe', 'Planifier la réunion hebdomadaire de l''équipe', 'DONE', 'MEDIUM', 3, NOW(), NOW(), DATEADD('DAY', -1, NOW())),
('Rédiger rapport', 'Finaliser le rapport mensuel d''activité', 'IN_PROGRESS', 'HIGH', 3, NOW(), NOW(), DATEADD('DAY', 2, NOW())),
('Formation continue', 'Suivre une formation sur les nouvelles technologies', 'TODO', 'LOW', 3, NOW(), NOW(), DATEADD('DAY', 30, NOW())),

('Optimiser base de données', 'Améliorer les performances des requêtes', 'TODO', 'HIGH', 4, NOW(), NOW(), DATEADD('DAY', 14, NOW())),
('Documentation technique', 'Rédiger la documentation de l''API', 'TODO', 'MEDIUM', 4, NOW(), NOW(), DATEADD('DAY', 21, NOW()));