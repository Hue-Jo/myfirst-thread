INSERT INTO article(title, content) VALUES ('가가가', '1111');
INSERT INTO article(title, content) VALUES ('나나나', '2222');
INSERT INTO article(title, content) VALUES ('다다다', '3333');

INSERT INTO article(title, content) VALUES ('당신의 인생영화는?', '댓글을 남겨주세요!');
INSERT INTO article(title, content) VALUES ('당신의 소울푸드는?', '댓글을 남겨주세요!!');
INSERT INTO article(title, content) VALUES ('당신의 취미는?', '댓글을 남겨주세요!!!');

INSERT INTO comment(article_id, nickname, body) VALUES (4, 'CRUD', '지구를 지켜라');
INSERT INTO comment(article_id, nickname, body) VALUES (4, 'JPA', '눈 먼 자들의 도시');
INSERT INTO comment(article_id, nickname, body) VALUES (4, 'HTML', '김씨 표류기');

INSERT INTO comment(article_id, nickname, body) VALUES (5, 'CRUD', '치킨');
INSERT INTO comment(article_id, nickname, body) VALUES (5, 'JPA', '샤브샤브');
INSERT INTO comment(article_id, nickname, body) VALUES (5, 'HTML', '초밥');

INSERT INTO comment(article_id, nickname, body) VALUES (6, 'CRUD', '운동');
INSERT INTO comment(article_id, nickname, body) VALUES (6, 'JPA', 'OTT 시청');
INSERT INTO comment(article_id, nickname, body) VALUES (6, 'HTML', '독서');