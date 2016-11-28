drop database crm;
create database crm;
grant all privileges on crm.* to 'crm'@'localhost' identified by 'crm';


#categorias
INSERT INTO categoria ( `codigo`, `nombre`, `descripcion`) VALUES ( 7, 'natus', 'Aut quia recusandae nostrum laborum tempore architecto eveniet.');
INSERT INTO categoria ( `codigo`, `nombre`, `descripcion`) VALUES ( 3, 'quos', 'Provident facere suscipit rerum consectetur natus beatae eos.');
INSERT INTO categoria ( `codigo`, `nombre`, `descripcion`) VALUES ( 4, 'aliquid', 'Beatae praesentium rerum est expedita.');
INSERT INTO categoria ( `codigo`, `nombre`, `descripcion`) VALUES ( 7, 'recusandae', 'Qui ipsa possimus non sit velit amet dolor deserunt.');
INSERT INTO categoria ( `codigo`, `nombre`, `descripcion`) VALUES ( 6, 'tempore', 'Omnis quam molestiae expedita omnis repellat.');
INSERT INTO categoria ( `codigo`, `nombre`, `descripcion`) VALUES ( 5, 'harum', 'Incidunt et ad fuga delectus magni doloremque enim fugit.');
INSERT INTO categoria ( `codigo`, `nombre`, `descripcion`) VALUES ( 2, 'tenetur', 'Ullam dolore qui quia explicabo repellendus enim.');
INSERT INTO categoria ( `codigo`, `nombre`, `descripcion`) VALUES ( 5, 'sint', 'Eos animi voluptatibus dolor.');
INSERT INTO categoria ( `codigo`, `nombre`, `descripcion`) VALUES ( 8, 'non', 'Quo atque non adipisci laborum ipsum praesentium dolore.');
INSERT INTO categoria ( `codigo`, `nombre`, `descripcion`) VALUES ( 7, 'ut', 'Officia aperiam exercitationem et possimus illo.');
INSERT INTO categoria ( `codigo`, `nombre`, `descripcion`) VALUES ( 3, 'aut', 'Sint laboriosam nemo non ipsum aperiam nesciunt et.');
INSERT INTO categoria ( `codigo`, `nombre`, `descripcion`) VALUES ( 6, 'officiis', 'Explicabo laboriosam eum vero voluptas.');
INSERT INTO categoria ( `codigo`, `nombre`, `descripcion`) VALUES ( 7, 'porro', 'Rerum et sunt veritatis est.');
INSERT INTO categoria ( `codigo`, `nombre`, `descripcion`) VALUES ( 6, 'omnis', 'Sit accusantium dignissimos et et commodi saepe.');
INSERT INTO categoria ( `codigo`, `nombre`, `descripcion`) VALUES ( 2, 'tempora', 'Expedita in sunt non sed tenetur quam quam.');
INSERT INTO categoria ( `codigo`, `nombre`, `descripcion`) VALUES ( 8, 'qui', 'Velit amet dicta libero dolores laboriosam.');
INSERT INTO categoria ( `codigo`, `nombre`, `descripcion`) VALUES ( 2, 'consequatur', 'Quasi non omnis voluptatem impedit qui.');
INSERT INTO categoria ( `codigo`, `nombre`, `descripcion`) VALUES ( 6, 'tenetur', 'Qui eum inventore eum fugiat expedita et aperiam.');
INSERT INTO categoria ( `codigo`, `nombre`, `descripcion`) VALUES ( 8, 'commodi', 'Repellendus sed placeat consequuntur laborum corrupti neque impedit.');
INSERT INTO categoria ( `codigo`, `nombre`, `descripcion`) VALUES ( 8, 'culpa', 'Quas et est labore unde minus.');





#productos
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '98', 'Kiehn, Bruen and Leuschke', 'Quos exercitationem fuga aperiam at.', '528.60041', 0, 10, 3, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/4719926b531ea035d39ddd34545d591e.jpg'), 'image/jpeg', 0);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '3212', 'Kuvalis-Gleichner', 'Animi ut consequatur repellendus.', '79453.8', 44, 13, 1, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/7983183a4d21f0da4b844999f7b3de83.jpg'), 'image/jpeg', 1);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '', 'Hegmann-Nikolaus', 'Quo debitis eum dolores qui a.', '374890691.99982', 3981020, 2, 2, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/881c68c10cd223842540408485a6d6d8.jpg'), 'image/jpeg', 0);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '7793225', 'Swaniawski LLC', 'Doloremque ad maxime autem maiores.', '8673358.043318', 881116, 16, 1, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/49009456500da09ce7774baa33c4d453.jpg'), 'image/jpeg', 0);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '2', 'Fahey, Kemmer and Torphy', 'Tenetur nemo itaque quasi earum ut perferendis quia.', '32191902.478013', 4, 20, 2, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/e24fdaac440358d8ccd8b81f2b924186.jpg'), 'image/jpeg', 1);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '9', 'Quigley Ltd', 'Voluptatum corporis debitis sed dolores.', '18608901.874338', 3, 3, 5, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/855b37b74073e07dc05aa3b39ac785d4.jpg'), 'image/jpeg', 1);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '99928', 'Botsford and Sons', 'Sunt soluta est aspernatur deserunt.', '644.244978866', 0, 3, 1, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/03623638e9697732ed91d1a37e4c6a37.jpg'), 'image/jpeg', 1);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '114824', 'Barton, Steuber and Walker', 'Dolorem voluptatem consequatur est placeat.', '4651.6', 529, 1, 3, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/cda34fce79fcc22811bb286eb7206862.jpg'), 'image/jpeg', 0);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '311458', 'McLaughlin Group', 'Explicabo itaque quibusdam quas omnis ab inventore id repellat.', '2.013855189', 0, 15, 1, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/557a62dc98d4436206a9808f04dbf199.jpg'), 'image/jpeg', 0);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '935256248', 'Graham-Kris', 'Aut quis accusamus eum.', '38116.630543114', 8868, 20, 1, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/0d88ea736c25e686dd44e7e9d7a645cc.jpg'), 'image/jpeg', 0);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '8684', 'Schiller, Boyle and Batz', 'Ad deleniti ullam consectetur vitae dolor.', '425', 38276, 18, 1, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/ec0d679a5478f200367a02039238cd2c.jpg'), 'image/jpeg', 1);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '4301871', 'Willms, Considine and Donnelly', 'Ut est quia voluptatem ut perferendis.', '46157.18777', 0, 15, 1, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/ef48f7c2dac6e5108b333053851f7c36.jpg'), 'image/jpeg', 1);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '7250', 'Jenkins PLC', 'Voluptas eum aut ut atque.', '327559.4989808', 801, 16, 3, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/d08b388f00a15efc335aaebb914fed44.jpg'), 'image/jpeg', 0);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '38', 'McLaughlin, Rosenbaum and Kuvalis', 'Ut atque qui soluta.', '0', 81, 20, 3, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/c6e3364dfb8a3195b2eb17038cb964fc.jpg'), 'image/jpeg', 0);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '5', 'Flatley, Cole and D\'Amore', 'Est qui reprehenderit error natus.', '558986.8239721', 5819, 15, 2, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/3251389754188508c5ddfd32c746d9fe.jpg'), 'image/jpeg', 1);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '', 'Hahn-Lesch', 'Eaque quasi error ad et aperiam et molestias.', '0', 87767715, 20, 1, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/b05a787b9928f17a548012cceb5d2938.jpg'), 'image/jpeg', 0);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '77812556', 'Lebsack-Eichmann', 'In aut tenetur debitis quo est maiores.', '4.51188528', 4184, 11, 3, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/81e405475f155a15dc3a04d193ea7e71.jpg'), 'image/jpeg', 0);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '5993', 'Koepp PLC', 'Nostrum mollitia ut commodi eos inventore.', '0', 130929, 18, 1, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/517d62bc30da78afb80528e13e45b27e.jpg'), 'image/jpeg', 1);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '440', 'Bradtke-Howe', 'Vel ullam error impedit ut quos sequi.', '787.44887008', 48633696, 5, 1, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/2ef01a6f439b3e224b99c694b96d544e.jpg'), 'image/jpeg', 1);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '564', 'Wolf, Dickens and Abbott', 'Aliquid nam nostrum nobis non ducimus.', '3.83461', 134, 9, 3, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/6e70bec9972cc15e48d52d4f196099bb.jpg'), 'image/jpeg', 0);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '3137', 'Little LLC', 'Et odio officiis voluptatem cum.', '2.040047', 9840, 15, 2, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/e54b80f68ef17acc37218fe268255ec7.jpg'), 'image/jpeg', 1);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '208535244', 'McGlynn, Torp and Balistreri', 'Modi voluptates cum consequatur vitae omnis dolorem consequatur aut.', '1.528', 884751982, 19, 2, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/bb243b6619b81fd16fd100fa865073a0.jpg'), 'image/jpeg', 1);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '421992', 'Labadie, Buckridge and Howe', 'Accusantium veniam et vel nemo velit sunt fuga.', '0', 2061, 3, 2, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/ebaebce39d7397decbab784a0c7e14ec.jpg'), 'image/jpeg', 1);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '74', 'Kreiger, Lueilwitz and Nitzsche', 'Est temporibus eos suscipit aut.', '168102', 8829741, 5, 1, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/a8df1f58fcf27aa55c7ee8dfa7480418.jpg'), 'image/jpeg', 1);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '32', 'Balistreri Group', 'Molestias voluptates sint dolor repudiandae consectetur eum.', '0.51938', 405700153, 10, 1, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/36baace0b03db2bf5b0f05580f63c19f.jpg'), 'image/jpeg', 0);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '', 'Aufderhar-Collins', 'Reiciendis consequatur molestiae perspiciatis.', '5397.5297', 99937373, 15, 2, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/e21f98b5b328a1779d4e6c9fcdd22915.jpg'), 'image/jpeg', 1);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '', 'Collins, Renner and Murray', 'Esse beatae ratione quos.', '113911440.74655', 30, 1, 2, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/dcb9550fabcce85263469442909bd3ae.jpg'), 'image/jpeg', 0);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '753003602', 'Crona, McLaughlin and Powlowski', 'Dicta explicabo eum cupiditate facilis et et vitae quo.', '5760893.779', 3147880, 1, 3, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/7a3876e8c819b44fce1390fc11eac1eb.jpg'), 'image/jpeg', 1);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '5291', 'Russel-Abernathy', 'Magnam velit eveniet non aperiam dolor quo et alias.', '2.57259', 0, 10, 3, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/e3698b9daa5eaddf0d9f5fa992557d4c.jpg'), 'image/jpeg', 0);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '1423', 'Cole-Witting', 'Voluptas maiores quod qui facere ratione laudantium qui dolores.', '3069.9', 3552, 1, 2, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/3fa48eddaeb95e3c92693da4e5df841c.jpg'), 'image/jpeg', 1);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '29989324', 'Rosenbaum, Turcotte and Shields', 'Non ratione et voluptatem et sit adipisci doloribus.', '0', 1109, 2, 2, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/ab2934a98670192cd0572b2f09e58eae.jpg'), 'image/jpeg', 0);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '889', 'Blick-Mante', 'Ut accusamus est illo est rem.', '14.846425', 184924, 19, 3, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/107fd4708204368d9839113e2aee73a0.jpg'), 'image/jpeg', 0);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '1928887', 'Lubowitz-Ritchie', 'Voluptatem vero quia dolor ipsa animi ab.', '32.076', 523, 13, 1, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/4e74ac7dc42d4fb848323465cd851730.jpg'), 'image/jpeg', 0);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '13295854', 'Mann PLC', 'Dolorem fugit esse ratione nihil molestiae quae voluptatem.', '594.342814', 566116, 3, 1, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/bdcf19dd751ef78096a263a86aeaf18c.jpg'), 'image/jpeg', 0);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '61944', 'Jones-Bogan', 'Odio magnam esse et aliquam aut.', '0', 40286, 15, 1, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/c46f10e7f11834a9ead5d35e1267805f.jpg'), 'image/jpeg', 1);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '', 'Tromp, Trantow and Gleason', 'Est voluptatem non itaque illum fugit et eius.', '32390.8995', 1460362, 13, 3, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/f1719e91af22b192baabc2ed163429f2.jpg'), 'image/jpeg', 1);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '', 'Mertz-Ortiz', 'Et animi occaecati voluptatem sunt laborum.', '17098', 44882, 6, 1, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/01e3e7650841e28891a65ff231714acc.jpg'), 'image/jpeg', 1);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '1', 'Waelchi Ltd', 'Delectus eligendi nemo aliquam itaque debitis et.', '3723.0342561', 5517, 18, 2, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/9ea8df5ff0c8e0559d6741ab70a0b829.jpg'), 'image/jpeg', 0);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '443096281', 'Weimann-Trantow', 'Sed id nesciunt suscipit qui natus aut provident.', '416735', 217403186, 13, 3, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/c2bc87da06308444f9fb83020f5d5641.jpg'), 'image/jpeg', 0);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '25615', 'Stamm, Volkman and Kuhn', 'Eos impedit sint sint aperiam quia praesentium nostrum.', '3022202.615', 9256, 20, 2, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/909db7a43dbc0c18cb78160997534190.jpg'), 'image/jpeg', 0);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '53415', 'Lebsack and Sons', 'Rerum amet nam harum temporibus illum quis.', '19.7', 52268, 17, 2, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/70a641b9ded1da24c19e02df1fc41c09.jpg'), 'image/jpeg', 1);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '2483', 'Stanton-West', 'Soluta libero ratione repellendus voluptatem ipsum aut est animi.', '12.822660366', 9773663, 11, 1, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/669ee0f3a65a56d3cf1b3dd10be7bca0.jpg'), 'image/jpeg', 1);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '991990785', 'Smith Group', 'Ipsum fuga quibusdam inventore aliquid occaecati illum laborum.', '1406314.85', 6, 15, 3, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/2eecc570b380a09f9218c346346ee9cb.jpg'), 'image/jpeg', 0);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '2097793', 'Ruecker Ltd', 'Libero cupiditate reprehenderit aut enim eum temporibus.', '3421.036813', 74595, 12, 2, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/f4bfeaeb511ed99ccb8f6320c8105bb1.jpg'), 'image/jpeg', 1);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '219', 'Emmerich Ltd', 'Aut aut reiciendis praesentium eaque quia possimus non.', '13993.54', 7, 10, 3, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/1d4effc8e03e8de2d52f5d18f1f3c0a3.jpg'), 'image/jpeg', 1);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '59087', 'Hudson-McDermott', 'Voluptates repudiandae est qui commodi dolorem nobis molestias nostrum.', '77127529.942434', 12523, 10, 1, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/4177a4c94b0f622dcaa58ad167e8f8d1.jpg'), 'image/jpeg', 0);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '84982', 'Heidenreich, Fadel and Barton', 'Impedit dolor temporibus inventore repellendus vero porro eius recusandae.', '48194.460938', 14, 11, 3, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/1c23d4279787bee611e3bea01bb93853.jpg'), 'image/jpeg', 0);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '70', 'Hilll, Watsica and Stamm', 'Officia adipisci dicta dolore consequatur.', '773176150.07667', 904441361, 1, 2, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/3332daa7c84b4372ba0625dc47c136bc.jpg'), 'image/jpeg', 0);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '6', 'Pouros LLC', 'Et enim ipsa est excepturi ut et quia.', '49580783.534338', 53, 7, 1, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/2e08810c6cbcab12afcc6cf1429bda33.jpg'), 'image/jpeg', 1);
INSERT INTO producto ( `codigo`, `nombre`, `descripcion`, `precio`, `existencias`, `categoria_id`, `genero`, `imagen`, `content_type_imagen`, `active`) VALUES ( '8054201', 'Gerlach-Leuschke', 'Quos nulla quia praesentium veritatis.', '1121204.6751337', 9739, 16, 1, LOAD_FILE('/home/samuel/crm-igf-cop/test/datos_test/86ffd962f6c05adda69bb4a5fbd3ebda.jpg'), 'image/jpeg', 0);





#ofertas
INSERT INTO oferta ( `codigo`, `nombre`, `fecha_inicio`, `fecha_fin`, `precio`, `descripcion`, `imagen`, `content_type_imagen`, `genero`, `active`) VALUES ('781', 'Facetoface even-keeled localareanetwork', '1990-11-29 00:00:00.000000', '2011-01-23 00:00:00.000000', '823', 'Ut voluptatem et dolorem delectus ratione.', '/home/samuel/test/datos_test/59e2a1e342e06010fe153026a840ad62.jpg', 'image/jpeg', 2, 0);
INSERT INTO oferta ( `codigo`, `nombre`, `fecha_inicio`, `fecha_fin`, `precio`, `descripcion`, `imagen`, `content_type_imagen`, `genero`, `active`) VALUES ('807', 'Configurable disintermediate alliance', '2011-08-05 00:00:00.000000', '1988-01-27 00:00:00.000000', '976', 'Qui corporis accusantium magni placeat saepe.', '/home/samuel/test/datos_test/28b1f8ea0146aaf46e69c665eb12d98a.jpg', 'image/jpeg', 2, 1);
INSERT INTO oferta ( `codigo`, `nombre`, `fecha_inicio`, `fecha_fin`, `precio`, `descripcion`, `imagen`, `content_type_imagen`, `genero`, `active`) VALUES ('319', 'Decentralized incremental paradigm', '1983-06-03 00:00:00.000000', '1980-11-05 00:00:00.000000', '17', 'Velit provident qui fugiat impedit velit soluta eaque eum.', '/home/samuel/test/datos_test/83d7e2a0162d3afbc8612cd17d3400f4.jpg', 'image/jpeg', 2, 1);
INSERT INTO oferta ( `codigo`, `nombre`, `fecha_inicio`, `fecha_fin`, `precio`, `descripcion`, `imagen`, `content_type_imagen`, `genero`, `active`) VALUES ('650', 'Object-based multi-tasking hub', '2003-12-01 00:00:00.000000', '1989-02-09 00:00:00.000000', '366', 'Aut voluptas incidunt sit unde est eos itaque voluptatem.', '/home/samuel/test/datos_test/52207b5cf77cc7fd25a85edbf9756d4d.jpg', 'image/jpeg', 1, 1);
INSERT INTO oferta ( `codigo`, `nombre`, `fecha_inicio`, `fecha_fin`, `precio`, `descripcion`, `imagen`, `content_type_imagen`, `genero`, `active`) VALUES ('801', 'Universal mission-critical algorithm', '2000-10-28 00:00:00.000000', '1991-04-29 00:00:00.000000', '129', 'Voluptas accusantium porro expedita veniam recusandae.', '/home/samuel/test/datos_test/c9fd8eda718e5d9d6e2fe28337952404.jpg', 'image/jpeg', 1, 0);
INSERT INTO oferta ( `codigo`, `nombre`, `fecha_inicio`, `fecha_fin`, `precio`, `descripcion`, `imagen`, `content_type_imagen`, `genero`, `active`) VALUES ('438', 'Adaptive executive application', '2001-05-07 00:00:00.000000', '1984-11-18 00:00:00.000000', '195', 'Aut voluptas vero quia omnis eos et nam.', '/home/samuel/test/datos_test/fb92fe01399ac67f7c68ca498e1e8a6c.jpg', 'image/jpeg', 1, 1);
INSERT INTO oferta ( `codigo`, `nombre`, `fecha_inicio`, `fecha_fin`, `precio`, `descripcion`, `imagen`, `content_type_imagen`, `genero`, `active`) VALUES ('306', 'Enhanced systemic utilisation', '1989-02-17 00:00:00.000000', '2001-04-29 00:00:00.000000', '71', 'Deleniti vel eum officia ullam laborum sint.', '/home/samuel/test/datos_test/52283ea6e757878415fcd80015db18af.jpg', 'image/jpeg', 3, 1);
INSERT INTO oferta ( `codigo`, `nombre`, `fecha_inicio`, `fecha_fin`, `precio`, `descripcion`, `imagen`, `content_type_imagen`, `genero`, `active`) VALUES ('524', 'Customer-focused bandwidth-monitored knowledgebase', '1992-08-14 00:00:00.000000', '1996-02-07 00:00:00.000000', '755', 'Velit porro sed sed quis corporis quo velit sapiente.', '/home/samuel/test/datos_test/3cd7b294980f23e2c977c1f0acc1b333.jpg', 'image/jpeg', 3, 1);
INSERT INTO oferta ( `codigo`, `nombre`, `fecha_inicio`, `fecha_fin`, `precio`, `descripcion`, `imagen`, `content_type_imagen`, `genero`, `active`) VALUES ('417', 'Visionary tertiary model', '2006-02-13 00:00:00.000000', '1992-05-24 00:00:00.000000', '10', 'Omnis voluptas molestiae et possimus illo nam praesentium.', '/home/samuel/test/datos_test/8afd2af24343116fcf0dbca410ba9db8.jpg', 'image/jpeg', 3, 0);
INSERT INTO oferta ( `codigo`, `nombre`, `fecha_inicio`, `fecha_fin`, `precio`, `descripcion`, `imagen`, `content_type_imagen`, `genero`, `active`) VALUES ('497', 'User-friendly well-modulated projection', '1975-02-09 00:00:00.000000', '1971-05-24 00:00:00.000000', '592', 'Id est dolor eos veritatis cumque.', '/home/samuel/test/datos_test/3ff8428ea0c840962691b93364048a1d.jpg', 'image/jpeg', 1, 0);
INSERT INTO oferta ( `codigo`, `nombre`, `fecha_inicio`, `fecha_fin`, `precio`, `descripcion`, `imagen`, `content_type_imagen`, `genero`, `active`) VALUES ('540', 'Distributed exuding hierarchy', '1982-11-19 00:00:00.000000', '1994-01-10 00:00:00.000000', '590', 'Voluptatem reprehenderit rerum cum doloremque repellendus quasi nesciunt.', '/home/samuel/test/datos_test/97d5896f009d803af4055f3b2bb69f92.jpg', 'image/jpeg', 1, 1);
INSERT INTO oferta ( `codigo`, `nombre`, `fecha_inicio`, `fecha_fin`, `precio`, `descripcion`, `imagen`, `content_type_imagen`, `genero`, `active`) VALUES ('905', 'Programmable human-resource utilisation', '1994-06-08 00:00:00.000000', '1978-08-18 00:00:00.000000', '32', 'Autem dolorum laborum sed.', '/home/samuel/test/datos_test/910ac7f6e14a537e2bb28e33893cd3c0.jpg', 'image/jpeg', 2, 0);
INSERT INTO oferta ( `codigo`, `nombre`, `fecha_inicio`, `fecha_fin`, `precio`, `descripcion`, `imagen`, `content_type_imagen`, `genero`, `active`) VALUES ('692', 'Synergized scalable software', '1972-05-16 00:00:00.000000', '1976-04-25 00:00:00.000000', '220', 'Architecto et blanditiis aut mollitia omnis ut sapiente blanditiis.', '/home/samuel/test/datos_test/e7801bc93fcf3822739025df125d1ded.jpg', 'image/jpeg', 2, 1);
INSERT INTO oferta ( `codigo`, `nombre`, `fecha_inicio`, `fecha_fin`, `precio`, `descripcion`, `imagen`, `content_type_imagen`, `genero`, `active`) VALUES ('658', 'Enterprise-wide systemic synergy', '2007-03-09 00:00:00.000000', '2002-03-19 00:00:00.000000', '906', 'Quis possimus quod ut repudiandae et dicta dolores.', '/home/samuel/test/datos_test/436ec355ad4f476896a6b52dd4e51cdb.jpg', 'image/jpeg', 1, 1);
INSERT INTO oferta ( `codigo`, `nombre`, `fecha_inicio`, `fecha_fin`, `precio`, `descripcion`, `imagen`, `content_type_imagen`, `genero`, `active`) VALUES ('318', 'Front-line transitional extranet', '2005-11-16 00:00:00.000000', '1996-04-28 00:00:00.000000', '509', 'Laborum odio maxime a eos cupiditate qui.', '/home/samuel/test/datos_test/18d25f94a075ab9e8e046efd8e60ecc0.jpg', 'image/jpeg', 3, 1);
INSERT INTO oferta ( `codigo`, `nombre`, `fecha_inicio`, `fecha_fin`, `precio`, `descripcion`, `imagen`, `content_type_imagen`, `genero`, `active`) VALUES ('190', 'Balanced even-keeled hardware', '1973-11-21 00:00:00.000000', '1975-06-01 00:00:00.000000', '429', 'Sit qui minus iste veniam.', '/home/samuel/test/datos_test/995d63defc523fe566b92b3507c61f8c.jpg', 'image/jpeg', 2, 0);
INSERT INTO oferta ( `codigo`, `nombre`, `fecha_inicio`, `fecha_fin`, `precio`, `descripcion`, `imagen`, `content_type_imagen`, `genero`, `active`) VALUES ('102', 'Horizontal context-sensitive concept', '2011-06-13 00:00:00.000000', '2005-02-12 00:00:00.000000', '390', 'Et corporis velit repudiandae consequuntur voluptatum quia consequatur.', '/home/samuel/test/datos_test/3bb0dc221e0b467a5150b11864ba29c5.jpg', 'image/jpeg', 1, 1);
INSERT INTO oferta ( `codigo`, `nombre`, `fecha_inicio`, `fecha_fin`, `precio`, `descripcion`, `imagen`, `content_type_imagen`, `genero`, `active`) VALUES ('215', 'Virtual heuristic standardization', '1993-07-11 00:00:00.000000', '1997-09-21 00:00:00.000000', '708', 'Eum consequatur ut odio nesciunt natus.', '/home/samuel/test/datos_test/58cc28fca90954144277e626da9948e8.jpg', 'image/jpeg', 3, 0);
INSERT INTO oferta ( `codigo`, `nombre`, `fecha_inicio`, `fecha_fin`, `precio`, `descripcion`, `imagen`, `content_type_imagen`, `genero`, `active`) VALUES ('437', 'Profit-focused foreground paradigm', '1971-07-07 00:00:00.000000', '2002-07-24 00:00:00.000000', '685', 'Sequi beatae reprehenderit sapiente vel vitae a nisi rerum.', '/home/samuel/test/datos_test/9e72a10c44944e1e6d4e7c6ea7712f69.jpg', 'image/jpeg', 2, 1);
INSERT INTO oferta ( `codigo`, `nombre`, `fecha_inicio`, `fecha_fin`, `precio`, `descripcion`, `imagen`, `content_type_imagen`, `genero`, `active`) VALUES ('942', 'Organic cohesive success', '1977-08-31 00:00:00.000000', '1988-12-12 00:00:00.000000', '629', 'Deleniti et omnis quisquam dolor laboriosam possimus.', '/home/samuel/test/datos_test/3461fc5005183200c4b6ab37c877f64c.jpg', 'image/jpeg', 2, 0);

#producto_join_oferta
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 12, 13, 1);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 14, 4, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 50, 14, 2);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 14, 16, 4);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 39, 2, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 44, 13, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 38, 20, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 6, 11, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 35, 10, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 12, 10, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 35, 3, 2);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 42, 10, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 46, 13, 4);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 48, 12, 5);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 27, 11, 2);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 12, 9, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 8, 1, 5);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 14, 10, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 39, 7, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 7, 6, 4);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 35, 2, 2);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 29, 4, 1);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 44, 7, 4);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 25, 16, 4);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 45, 16, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 4, 20, 5);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 43, 6, 2);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 21, 3, 1);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 22, 15, 5);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 33, 16, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 15, 8, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 42, 15, 2);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 22, 3, 5);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 12, 5, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 23, 1, 5);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 28, 15, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 16, 18, 1);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 31, 10, 4);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 47, 19, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 20, 10, 4);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 38, 17, 1);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 39, 13, 1);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 46, 6, 2);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 36, 12, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 49, 13, 5);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 39, 6, 2);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 12, 15, 4);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 34, 17, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 38, 12, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 39, 8, 5);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 18, 19, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 18, 11, 1);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 30, 7, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 40, 17, 1);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 40, 8, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 46, 5, 1);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 40, 9, 5);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 16, 20, 4);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 11, 18, 4);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 28, 7, 2);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 10, 8, 2);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 12, 9, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 33, 19, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 21, 4, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 30, 20, 2);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 47, 16, 2);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 42, 3, 2);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 38, 12, 2);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 49, 20, 5);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 18, 15, 5);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 32, 10, 2);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 13, 4, 2);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 7, 19, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 2, 1, 4);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 32, 4, 5);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 36, 17, 4);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 40, 5, 1);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 47, 8, 1);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 47, 7, 4);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 7, 6, 2);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 11, 8, 4);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 45, 13, 2);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 10, 3, 1);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 26, 18, 5);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 40, 18, 2);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 17, 20, 4);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 26, 18, 2);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 34, 6, 5);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 17, 6, 2);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 45, 5, 1);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 45, 13, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 43, 7, 2);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 21, 10, 4);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 18, 6, 2);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 4, 20, 3);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 33, 16, 2);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 36, 15, 4);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 26, 19, 5);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 29, 4, 2);
INSERT INTO producto_join_oferta ( `producto_id`, `oferta_id`, `cantidad`) VALUES ( 28, 17, 2);
