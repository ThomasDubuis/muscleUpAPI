
insert into customer(id, firstname, lastname, email, hashed_password, role, visibility, arm_size, chest_size, waist_size, thigh_size, penis_size, gender, created_at, updated_at) values ('c02ca07c-e44e-4abd-8d46-e4f39e1574fc', 'admin', 'admin', 'admin@gmail.com', '$2a$10$RjlpMWJsU5Ue8oWtPVZOwerUUWw0Jo1MlqAjbGXUnuLXz1S0PP4Ea', 'ADMIN', 'ALL', 20, 21, 22, 23, 24, 'MALE', '2024-06-27T11:58:10.758+00:00', '2024-06-27T11:58:10.758+00:00');
insert into customer(id, firstname, lastname, email, hashed_password, role, visibility, arm_size, chest_size, waist_size, thigh_size, penis_size, gender, created_at, updated_at) values ('0cb559e8-e44e-4abd-8d46-e4f39e1574fc', 'thomas', 'dubuis', 'thomasdubuis13@gmail.com', '$2a$10$RjlpMWJsU5Ue8oWtPVZOwerUUWw0Jo1MlqAjbGXUnuLXz1S0PP4Ea', 'USER', 'ALL', 20, 21, 22, 23, 24, 'MALE', '2024-06-27T11:58:10.758+00:00', '2024-06-27T11:58:10.758+00:00');
insert into customer(id, firstname, lastname, email, hashed_password, role, visibility, arm_size, chest_size, waist_size, thigh_size, penis_size, gender, created_at, updated_at) values ('b1c93565-d816-4217-a6dc-d61530f5df4b', 'Bryan', 'Battu', 'bryan.battu@gmail.com', '$2a$10$mSlcZVd3hRavdIyWk.eymuJeQxdwuf5SG3C.ZnBQkZmra8/zKXrui', 'USER', 'ALL', 38.5, 101.0, 83.5, 56.0, 14, 'MALE', '2024-06-27T11:58:10.758+00:00', '2024-06-27T11:58:10.758+00:00');

insert into gym(id, name, group_name, city, department, region, country) values ('d4594b41-306b-4798-b2d3-454944301956','Fitness Park', 'Fitness Park','Cabriès', 'Bouches-du-Rhône', 'PACA', 'France');
insert into gym(id, name, group_name, city, department, region, country) values ('76a57aad-72c6-4ad1-8d60-386bcf881bf3','Vita Liberté', 'Vita Liberté','Meyreuil', 'Bouches-du-Rhône', 'PACA', 'France');

insert into rank(id, category, grade, min_score) values ('4bf1a37e-7662-4f51-bb3d-2c379c559857', 'ASSIDUITY', 'IRON', 0);
insert into rank(id, category, grade, min_score) values ('c4f76c12-7214-40af-be0a-8428b458ae52', 'ASSIDUITY', 'BRONZE', 10);
insert into rank(id, category, grade, min_score) values ('06fa7acd-08b7-410c-83a9-b21f800a19b4', 'ASSIDUITY', 'SILVER', 30);
insert into rank(id, category, grade, min_score) values ('0cb559e8-00ae-436e-928e-ab46308ff3f2', 'ASSIDUITY', 'OR', 50);
insert into rank(id, category, grade, min_score) values ('a0940b0b-68ae-4fdc-9304-dc28ec0c78e3', 'ASSIDUITY', 'PLATINE', 75);
insert into rank(id, category, grade, min_score) values ('ed44e1c2-3ade-421c-88c5-c0db3a804734', 'ASSIDUITY', 'DIAMOND', 125);
insert into rank(id, category, grade, min_score) values ('c942b815-d76c-43c2-bd23-f3e7140f11bc', 'ASSIDUITY', 'MASTER', 250);
insert into rank(id, category, grade, min_score) values ('4ed905d1-28e7-4bc9-941a-a66a5f4785cb', 'PERFORMANCE', 'IRON', 0);
insert into rank(id, category, grade, min_score) values ('ab224bc1-4dec-4b46-8633-ad04a4906785', 'PERFORMANCE', 'BRONZE', 500);
insert into rank(id, category, grade, min_score) values ('29f40aec-840b-4d9f-951c-c0a743b30d8e', 'PERFORMANCE', 'SILVER', 1000);
insert into rank(id, category, grade, min_score) values ('ba899a83-9c35-4599-b6ea-93b05689430d', 'PERFORMANCE', 'OR', 1500);
insert into rank(id, category, grade, min_score) values ('e8f3801c-78d1-4dad-8b82-a6bf992e6d5f', 'PERFORMANCE', 'PLATINE', 3000);
insert into rank(id, category, grade, min_score) values ('32e2a9e2-a47e-4bf0-ac78-af9127393f18', 'PERFORMANCE', 'DIAMOND', 5000);
insert into rank(id, category, grade, min_score) values ('09cde1aa-3379-4387-8235-d41cf1606f1f', 'PERFORMANCE', 'MASTER', 10000);

insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('1a1044a0-d1b2-4052-9cf8-45761668ce91', 'Développé couché', 123.0, 10, 0.001, 'Description Exercice1');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('fffb371b-77b7-4e78-9ed9-a70678ab7826', 'Développé couché incliné', 123.0, 20.0, 0.001, 'Développé couché sur banc incliné');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('eec6f18c-0def-4058-9d65-caa1d640e448', 'Développé militaire', 123.0, 5.0, 0.001, 'Développé militaire pour les épaules');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('740e6eae-11a0-4867-a5a6-98b48bc85e4e', 'Squat', 123.0, 20.0, 0.001, 'Squat sur les pieds');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('a4ed8367-c0f2-49dc-91e8-fe5af07cfd1f', 'Curl marteau', 123.0, 5.0, 0.001, 'Curl avec le marteau de ton daron que t as dans le garage');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('77feea7f-d714-452d-9967-4d707dc0d4d2', 'Traction', 123.0, 50.0, 0.001, 'Tire sur ton dos');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('1a8c5f75-d6b8-4af1-a0bc-7841dd1bece6', 'Développé couché prise serrée', 160.0, 15, 0.001, 'Groupe musculaire: Triceps, Équipement: Barre');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('0fc9dd92-6c5e-44c8-8306-f613b7e857a6', 'Presse Arnold', 130.0, 15, 0.001, 'Groupe musculaire: Épaules, Équipement: Haltère');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('e2433c0f-96a3-44f6-b343-dbe9b26e9ab8', 'Soulevé de terre sumo', 132.5, 15, 0.001, 'Groupe musculaire: Quadriceps, Équipement: Barre');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('a28e9943-efd2-48d2-866f-e5ea4f593185', 'Haussement d''épaules', 112.5, 15, 0.001, 'Groupe musculaire: Trapèzes, Équipement: Barre');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('31459a46-bcdb-4411-87a6-2761a54ad3ea', 'Épaulé jeté', 135.0, 15, 0.001, 'Groupe musculaire: Corps complet, Équipement: Barre');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('13582ebc-f934-4a80-ba56-39f149b98a41', 'Pompes en équilibre sur les mains', 142.5, 10, 0.001, 'Groupe musculaire: Épaules, Équipement: Aucun');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('cb9ca8ac-f02a-4fab-80fd-865a9b91b674', 'Levier avant', 142.5, 10, 0.001, 'Groupe musculaire: Abdominaux, Équipement: Aucun');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('4230e8f0-914c-4977-8c98-667016fc1bea', 'Dips triceps (avec poids)', 155.0, 15, 0.001, 'Groupe musculaire: Triceps, Équipement: Machine');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('e0bf52f7-1cb0-4337-9077-7e4767a7071d', 'Pompes à un bras', 137.5, 10, 0.001, 'Groupe musculaire: Corps complet, Équipement: Aucun');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('54c12fc2-0a9b-401a-839c-8b9b5d7b9577', 'Traction sternum (Gironda)', 120.0, 10, 0.001, 'Groupe musculaire: Dorsaux, Équipement: Aucun');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('8f742504-7247-4e7f-8f20-bd6475b7ec55', 'Rowing penché (Barre)', 140.0, 15, 0.001, 'Groupe musculaire: Dos, Équipement: Barre');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('d3775e27-f9ab-43f3-b676-8d1a6e3c758d', 'Swing avec kettlebell', 100.0, 15, 0.001, 'Groupe musculaire: Corps complet, Équipement: Kettlebell');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('b3b7f444-434e-4a56-a8c1-1e6b1a5b7ad6', 'Soulevé de terre roumain', 145.0, 15, 0.001, 'Groupe musculaire: Ischio-jambiers, Équipement: Haltère');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('05fb34a9-e9ae-4c05-b7e1-75e3a5e8df04', 'Pompes en pike', 95.0, 10, 0.001, 'Groupe musculaire: Épaules, Équipement: Aucun');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('6fa1b557-57d4-400d-a890-2d48edee3b46', 'Face Pull', 110.0, 15, 0.001, 'Groupe musculaire: Épaules, Équipement: Câble');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('90f1f1be-5480-4d2e-8d44-7918eae3b9f2', 'Soulevé de terre', 145.0, 15, 0.001, 'Groupe musculaire: Dos, Équipement: Barre');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('8c00b9b1-5aaf-4b43-90a6-b2bb51c4d3e4', 'Fente', 130.0, 15, 0.001, 'Groupe musculaire: Jambes, Équipement: Haltère');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('1cc6d2f3-bd55-42fa-8e61-d84a3be4e9a2', 'Pompes', 120.0, 10, 0.001, 'Groupe musculaire: Poitrine, Équipement: Aucun');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('2e46a204-4bfa-49a1-8d85-77b1c5b0a68d', 'Presse jambes', 145.0, 15, 0.001, 'Groupe musculaire: Jambes, Équipement: Machine');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('3f548315-1c58-47d5-9235-57b7d6e1a273', 'Écarté couché', 135.0, 15, 0.001, 'Groupe musculaire: Poitrine, Équipement: Haltère');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('4f655426-56c9-4987-bb77-c29b3b29c3e5', 'Curl biceps', 110.0, 15, 0.001, 'Groupe musculaire: Biceps, Équipement: Haltère');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('5f766537-1d7e-4e89-95ad-7a9e4b17e3f6', 'Extension triceps', 120.0, 15, 0.001, 'Groupe musculaire: Triceps, Équipement: Câble');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('6f877648-4e3a-4f8b-8f9d-9b8e5b38e407', 'Presse épaules', 130.0, 15, 0.001, 'Groupe musculaire: Épaules, Équipement: Haltère');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('7f988759-2f4b-4f98-a1a8-c49e6b19f518', 'Tirage vertical', 115.0, 15, 0.001, 'Groupe musculaire: Dos, Équipement: Machine');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('8fa0996a-3f5c-409a-b28b-e67e7c4b9629', 'Élévation mollets', 105.0, 15, 0.001, 'Groupe musculaire: Mollets, Équipement: Machine');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('9fa1aa7b-1f6d-4d89-a29c-5a7e8f1b073b', 'Rowing assis', 125.0, 15, 0.001, 'Groupe musculaire: Dos, Équipement: Machine');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('a0a2bb8c-4f7e-4e9a-a3ad-5b8f6f2c184b', 'Leg Curl', 120.0, 15, 0.001, 'Groupe musculaire: Ischio-jambiers, Équipement: Machine');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('b0b3cc9d-0e8a-4f9a-b4ae-7b9f7f3d295c', 'Leg Extension', 115.0, 15, 0.001, 'Groupe musculaire: Quadriceps, Équipement: Machine');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('d0d5eeaf-5f0c-4fbc-b6c1-9c1f9f5e4a7e', 'Développé couché décliné', 135.0, 15, 0.001, 'Groupe musculaire: Poitrine, Équipement: Barre');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('f0f700c1-9f2e-4fed-b8e3-1d3f0f7e6a90', 'Curl pupitre', 125.0, 15, 0.001, 'Groupe musculaire: Biceps, Équipement: Machine');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('a1a801d2-1f3e-4fef-b9f4-2d4f0f8e7a91', 'Skull Crusher', 140.0, 15, 0.001, 'Groupe musculaire: Triceps, Équipement: Barre');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('b1b902e3-3f4f-4ff0-ba05-3d5f0f9e8a92', 'Dips poitrine', 120.0, 10, 0.001, 'Groupe musculaire: Poitrine, Équipement: Aucun');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('c1c013f4-5f60-5ff1-bb16-4e6f0f0e9a93', 'Développé incliné avec haltères', 135.0, 15, 0.001, 'Groupe musculaire: Poitrine, Équipement: Haltère');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('d1d12405-6f71-60f2-bc27-5f7f0f1e0a94', 'Développé décliné avec haltères', 130.0, 15, 0.001, 'Groupe musculaire: Poitrine, Équipement: Haltère');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('e1e23516-7f82-71f3-bd38-6f8f0f2e1a95', 'Twist russe', 115.0, 15, 0.001, 'Groupe musculaire: Abdominaux, Équipement: Haltère');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('f1f34627-8f93-82f4-be49-7f9f0f3e2a96', 'Relevé de jambes suspendu', 125.0, 10, 0.001, 'Groupe musculaire: Abdominaux, Équipement: Barre');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('05fb34a9-e9ae-4c05-b7e1-75e3a5e8df04', 'Montées de genoux', 140.0, 10, 0.001, 'Groupe musculaire: Abdominaux, Équipement: Aucun');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('6fa1b557-57d4-400d-a890-2d48edee3b46', 'Planche', 112.5, 10, 0.001, 'Groupe musculaire: Abdominaux, Équipement: Aucun');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('8cf78150-9062-4e77-aec4-d861fa19c93d', 'Planche latérale', 125.0, 10, 0.001, 'Groupe musculaire: Abdominaux, Équipement: Aucun');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('63a141e1-72f1-4ff5-9fce-e53a1b11eb4f', 'Superman', 120.0, 10, 0.001, 'Groupe musculaire: Bas du dos, Équipement: Aucun');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('2ded5073-d560-435d-9fd9-61d02e6207aa', 'Crunch bicycle', 135.0, 10, 0.001, 'Groupe musculaire: Abdominaux, Équipement: Aucun');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('34c0166a-9b57-4e40-90be-e1150ce6c141', 'Crunch', 115.0, 10, 0.001, 'Groupe musculaire: Abdominaux, Équipement: Aucun');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('ea32fb81-8591-4568-bc04-2b81ae270f49', 'Relevé de jambes', 122.5, 10, 0.001, 'Groupe musculaire: Abdominaux, Équipement: Aucun');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('e3a46e40-c616-47a1-be86-69d2c1cbb315', 'Hip Thrust', 125.0, 15, 0.001, 'Groupe musculaire: Fessiers, Équipement: Barre');
insert into exercise(id, name, one_rep_score, basic_weight, weight_multiplier, description) values ('0e30541a-61f7-47d9-ac89-b7f57d5b321c', 'Pont fessier', 130.0, 10, 0.001, 'Groupe musculaire: Fessiers, Équipement: Aucun');
