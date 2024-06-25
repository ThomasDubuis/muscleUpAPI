
insert into customer(id, firstname, lastname, email, hashed_password, role, visibility, arm_size, chest_size, waist_size, thigh_size, penis_size, gender) values ('c02ca07c-e44e-4abd-8d46-e4f39e1574fc', 'admin', 'admin', 'admin@gmail.com', '$2a$10$RjlpMWJsU5Ue8oWtPVZOwerUUWw0Jo1MlqAjbGXUnuLXz1S0PP4Ea', 'ADMIN', 'ALL', 20, 21, 22, 23, 24, 'MALE');
insert into customer(id, firstname, lastname, email, hashed_password, role, visibility, arm_size, chest_size, waist_size, thigh_size, penis_size, gender) values ('0cb559e8-e44e-4abd-8d46-e4f39e1574fc', 'thomas', 'dubuis', 'thomasdubuis13@gmail.com', '$2a$10$RjlpMWJsU5Ue8oWtPVZOwerUUWw0Jo1MlqAjbGXUnuLXz1S0PP4Ea', 'USER', 'ALL', 20, 21, 22, 23, 24, 'MALE');

insert into gym(id, name, group_name, city, department, region, country) values ('d4594b41-306b-4798-b2d3-454944301956','Fitness Park', 'Fitness Park','Cabriès', 'Bouches-du-Rhône', 'PACA', 'France');
insert into gym(id, name, group_name, city, department, region, country) values ('76a57aad-72c6-4ad1-8d60-386bcf881bf3','Vita Liberté', 'Vita Liberté','Meyreuil', 'Bouches-du-Rhône', 'PACA', 'France');

insert into rank(id, category, grade, min_score) values ('4bf1a37e-7662-4f51-bb3d-2c379c559857', 'ASSIDUITY', 'IRON', 0);
insert into rank(id, category, grade, min_score) values ('c4f76c12-7214-40af-be0a-8428b458ae52', 'ASSIDUITY', 'BRONZE', 50);
insert into rank(id, category, grade, min_score) values ('06fa7acd-08b7-410c-83a9-b21f800a19b4', 'ASSIDUITY', 'SILVER', 100);
insert into rank(id, category, grade, min_score) values ('0cb559e8-00ae-436e-928e-ab46308ff3f2', 'ASSIDUITY', 'OR', 150);
insert into rank(id, category, grade, min_score) values ('a0940b0b-68ae-4fdc-9304-dc28ec0c78e3', 'ASSIDUITY', 'PLATINE', 300);
insert into rank(id, category, grade, min_score) values ('ed44e1c2-3ade-421c-88c5-c0db3a804734', 'ASSIDUITY', 'DIAMOND', 500);
insert into rank(id, category, grade, min_score) values ('c942b815-d76c-43c2-bd23-f3e7140f11bc', 'ASSIDUITY', 'MASTER', 1000);
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
