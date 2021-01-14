insert into code_group (name, description) values ('성별코드', '성별을 표시')
insert into code_group (name, description) values ('방문상태코드', '환자 방문의 상태(방문중, 종료, 취소)')
insert into code_group (name, description) values ('진료과목코드', '진료과목(내과, 안과 등)')
insert into code_group (name, description) values ('진료유형코드', '진료의 유형(약처방, 검사 등)')

insert into code (code_group_name, code, code_name) values ('성별코드', 'M', '남')
insert into code (code_group_name, code, code_name) values ('성별코드', 'F', '여')
insert into code (code_group_name, code, code_name) values ('방문상태코드', '1', '방문중')
insert into code (code_group_name, code, code_name) values ('방문상태코드', '2', '종료')
insert into code (code_group_name, code, code_name) values ('방문상태코드', '3', '취소')
insert into code (code_group_name, code, code_name) values ('진료과목코드', '01', '내과')
insert into code (code_group_name, code, code_name) values ('진료과목코드', '02', '안과')
insert into code (code_group_name, code, code_name) values ('진료유형코드', 'D', '약처방')
insert into code (code_group_name, code, code_name) values ('진료유형코드', 'T', '검사')

insert into hospital (name, nursing_institution_number, chief_name) values ('에이치디정션내과', '31100554', '김소연')
insert into hospital (name, nursing_institution_number, chief_name) values ('스프링피부과', '31100678', '스프링')

insert into patient (name, registration_number, sex_code, birth_date, phone_number, hospital_id) values ('환자1', 'reg_no_1', 'F', '1989-11-18', '010-2872-4136', 1)
insert into patient (name, registration_number, sex_code, birth_date, phone_number, hospital_id) values ('환자2', 'reg_no_2', 'M', '2009-11-18', '010-2872-4136', 1)

insert into visit (hospital_id, patient_id, reception_date_time, status_code) values (1, 2, '2021-01-12', '종료')
insert into visit (hospital_id, patient_id, reception_date_time, status_code) values (1, 2, '2021-01-14', '방문중')
