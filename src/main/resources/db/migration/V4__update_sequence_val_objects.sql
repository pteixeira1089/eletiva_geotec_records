--Update the feeling_id to be sequential
SELECT setval('records.feeling_feeling_id_seq', (SELECT MAX(feeling_id) FROM records.feeling));

--Update the category_id to be sequential
SELECT setval('records.category_category_id_seq', (SELECT MAX(category_id) FROM records.category));