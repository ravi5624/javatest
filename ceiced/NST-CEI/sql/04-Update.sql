ALTER table attachments ADD COLUMN attachment_type VARCHAR(15) DEFAULT 'APPLICATION';
ALTER TABLE supervisor_examination_form RENAME COLUMN preferred_exam_center TO preferred_exam_centre;
ALTER TABLE wireman_examination_form RENAME COLUMN preferredExamCenter TO preferred_exam_centre;

ALTER TABLE contractor_license_form add column form_i_id BIGINT REFERENCES form_i_form(form_id);
ALTER TABLE lae_accounts ADD COLUMN unique_id VARCHAR(100);
ALTER TABLE lae_accounts ADD COLUMN reset_password VARCHAR(100);
ALTER TABLE lae_accounts ADD COLUMN cell_otp VARCHAR(100);
ALTER TABLE lae_accounts ADD COLUMN email_code VARCHAR(100);
ALTER TABLE lae_accounts ADD COLUMN otp_validated BOOLEAN DEFAULT FALSE;
ALTER TABLE lae_accounts ADD COLUMN email_validated BOOLEAN DEFAULT FALSE;

ALTER TABLE form_i_form ADD COLUMN contractor_lic_no VARCHAR(100);

/*Vishal: 5 FEB 2018: Query Replied.*/
ALTER TABLE form_queries ADD COLUMN remarks VARCHAR(500);

ALTER TABLE buffer_detail ADD COLUMN buffer_type VARCHAR(50);
ALTER TABLE buffer_detail ADD COLUMN other_type VARCHAR(50);


CREATE SEQUENCE talukas_seq INCREMENT 1 START 1;
CREATE TABLE talukas(
  id         BIGINT DEFAULT nextval('talukas_seq') PRIMARY KEY,
  taluka_code character varying(255),
  taluka_name character varying(255),
  district_id bigint REFERENCES districts(id)
);

ALTER TABLE wireman_exemption_form ADD COLUMN no_permit BOOLEAN DEFAULT FALSE;
ALTER TABLE supervisor_exemption_form ADD COLUMN no_permit BOOLEAN DEFAULT FALSE;


/*Sagar: 12 FEB 2018: Updated column type*/
ALTER TABLE escalator_installation ADD COLUMN escalator_site_name VARCHAR(255) DEFAULT "";
ALTER TABLE escalator_installation ALTER COLUMN construction_details TYPE TEXT;
ALTER TABLE escalator_installation ALTER COLUMN description TYPE TEXT;

ALTER TABLE report_accident ALTER COLUMN detail_accident_causes TYPE TEXT;
ALTER TABLE report_accident ALTER COLUMN taken_action TYPE TEXT;
ALTER TABLE report_accident ALTER COLUMN accident_evidence TYPE TEXT;
ALTER TABLE report_accident ALTER COLUMN remarks TYPE TEXT;
ALTER TABLE lift_escl_modification add COLUMN change_buiding_name TEXT;
alter table em_agency_license drop COLUMN establishment_year
alter table em_agency_license add column establishment_year numeric(10,0)
/*15_02_2018 */
alter table om_agency_license drop COLUMN establishment_year
alter table om_agency_license add column establishment_year numeric(10,0)

alter table it_agency_license drop COLUMN it_agency_license
alter table it_agency_license add column establishment_year numeric(10,0)

/*Sagar: 15 FEB 2018: added column*/
ALTER TABLE form_i_employer ADD COLUMN is_new BOOLEAN DEFAULT true;
ALTER TABLE report_accident ADD COLUMN is_authorized BOOLEAN DEFAULT false;

/*Sagar: 16 FEB 2018: added column*/
ALTER TABLE form_address ADD COLUMN user_key bigint;
ALTER TABLE form_i_employer ADD COLUMN other_attachment_name varchar(255);

/*Vimla: 16 FEB 2018: added column*/
ALTER TABLE form_detail ADD COLUMN can_Submit BOOLEAN DEFAULT false;

/*Sagar: 17 FEB 2018: added column*/
ALTER TABLE lift_installation ADD COLUMN agency_id bigint;
ALTER TABLE escalator_installation ADD COLUMN agency_id bigint;
ALTER TABLE operating_lift ADD COLUMN agency_id bigint;
ALTER TABLE operating_escalator ADD COLUMN agency_id bigint;
alter table lift_installation add agency_id integer
alter table staff_employee add employee varchar(255)
/*Vimla: 19 FEB 2018: added column*/
alter table lift_installation add internal_car_size integer
alter table lift_installation add internal_car_size integer
ALTER TABLE agency_license_duplicate RENAME COLUMN otherreason TO reason_if_other

/*Sagar: 20 FEB 2018: added column*/
ALTER TABLE agency_detail ADD COLUMN agency_auth_no varchar(255);
ALTER TABLE agency_detail ADD COLUMN issue_date varchar(255);
ALTER TABLE agency_detail ADD COLUMN expiry_date varchar(255);
ALTER TABLE em_agency_license DROP COLUMN establishment_year;
ALTER TABLE em_agency_license ADD column establishment_year numeric(10,0);
ALTER TABLE om_agency_license DROP COLUMN establishment_year;
ALTER TABLE om_agency_license ADD column establishment_year numeric(10,0);
ALTER TABLE it_agency_license DROP COLUMN establishment_year;
ALTER TABLE it_agency_license ADD column establishment_year numeric(10,0);

/*Sagar: 21 FEB 2018: added column*/
ALTER TABLE lift_installation ADD column owner_submitted_on DATE;

/*Sagar: 26 FEB 2018: added column*/
ALTER TABLE lift_installation DROP column internal_car_size;
ALTER TABLE lift_installation ADD column internal_car_size bigint;

ALTER TABLE agency_detail ADD column mobile_number varchar(15);
ALTER TABLE agency_detail ADD column email varchar(255);
ALTER TABLE agency_detail ADD column house_no varchar(255);
ALTER TABLE agency_detail ADD column building varchar(255);
ALTER TABLE agency_detail ADD column taluka varchar(255);
ALTER TABLE agency_detail ADD column district varchar(255);
ALTER TABLE agency_detail ADD column state varchar(255);
ALTER TABLE agency_detail ADD column pincode varchar(255);
ALTER TABLE agency_detail ADD column addrLine1 varchar(255);
ALTER TABLE agency_detail ADD column addrLine2 varchar(255);
ALTER TABLE agency_detail ADD column village varchar(255);

/*vimla 27 Feb 2018:rename and modified column */
ALTER TABLE public.escalator_installation RENAME COLUMN commencement_ork TO commencement_work;
ALTER TABLE public.escalator_installation ALTER COLUMN commencement_work TYPE TIMESTAMP USING commencement_work::TIMESTAMP;
ALTER TABLE public.escalator_installation ALTER COLUMN completion_work TYPE TIMESTAMP USING completion_work::TIMESTAMP;

/*Vimla: 28 FEB 2018: added column*/
ALTER TABLE le_testing_instrument ADD COLUMN is_editable BOOLEAN DEFAULT FALSE;
ALTER TABLE le_safety_gadget ADD COLUMN is_editable BOOLEAN DEFAULT FALSE;
ALTER TABLE staff_employee ADD COLUMN is_editable BOOLEAN DEFAULT FALSE;

ALTER TABLE staff_employee ADD COLUMN serial_number integer;

ALTER TABLE agency_license_modification ADD COLUMN is_change_branch_office BOOLEAN DEFAULT FALSE;
ALTER TABLE agency_license_modification ADD COLUMN is_change_communication_details BOOLEAN DEFAULT FALSE;
ALTER TABLE agency_license_modification ADD COLUMN is_change_legal_status BOOLEAN DEFAULT FALSE;
ALTER TABLE agency_license_modification ADD COLUMN is_change_main_office BOOLEAN DEFAULT FALSE;
ALTER TABLE agency_license_modification ADD COLUMN is_change_saftyguards BOOLEAN DEFAULT FALSE;
ALTER TABLE agency_license_modification ADD COLUMN is_change_vehicle_details BOOLEAN DEFAULT FALSE;
ALTER TABLE agency_license_modification ADD COLUMN is_change_workshopoffice BOOLEAN DEFAULT FALSE;
ALTER TABLE agency_license_modification ADD COLUMN is_change_testinstrument BOOLEAN DEFAULT FALSE;
ALTER TABLE agency_license_modification ADD COLUMN is_staff_details_change BOOLEAN DEFAULT FALSE;

/*Sagar: 1 MARCH 2018: added column*/
ALTER TABLE escalator_installation ADD column owner_submitted_on DATE;
ALTER TABLE escalator_installation ADD column application_for varchar(255);
ALTER TABLE escalator_installation ADD column commencement_work DATE;
ALTER TABLE escalator_installation ADD column completion_work DATE;
ALTER TABLE escalator_installation ADD column escalator_license_number varchar(255);
ALTER TABLE operating_lift ADD column owner_submitted_on DATE;
ALTER TABLE operating_escalator ADD column owner_submitted_on DATE;
ALTER TABLE lift_installation ADD column other_category_lift varchar(255);
ALTER TABLE lift_installation ALTER COLUMN internal_car_size TYPE double precision;

/*Vimla: 3 Mar 2018: added column*/
ALTER TABLE le_testing_instrument RENAME COLUMN is_editable TO is_new;
ALTER TABLE le_safety_gadget RENAME COLUMN is_editable TO is_new;
ALTER TABLE staff_employee RENAME COLUMN is_editable TO is_new;

/*Sagar: 6 MARCH 2018: added column*/
ALTER TABLE user_notifications ALTER COLUMN body TYPE TEXT;
ALTER TABLE report_accident ADD COLUMN accident_for varchar(255);

/*Sagar: 7 MARCH 2018: added column*/
ALTER TABLE agency_detail ALTER COLUMN issue_date TYPE TIMESTAMP USING issue_date::TIMESTAMP;
ALTER TABLE agency_detail ADD COLUMN expiry_date TYPE TIMESTAMP USING expiry_date::TIMESTAMP;

/*Sagar: 12 MARCH 2018: added column*/
ALTER TABLE lae_accounts ADD column contact_number varchar(15);

/*Sagar: 14 MARCH 2018: added column*/
ALTER TABLE lae_accounts ADD column expiry_time TIMESTAMP;

/*Vijay: 20 MARCH 2018: added column*/
ALTER TABLE form_detail ADD column other_comments text;

/*Sagar: 20 MARCH 2018: added column*/
ALTER TABLE lae_accounts ADD column birth_date DATE;

/*Vijay: 28 MARCH 2018: added column*/
ALTER TABLE supervisor_modification_form ADD column birth_date DATE;
ALTER TABLE wireman_modification_form ADD column birth_date DATE;
ALTER TABLE wireman_modification_form ADD COLUMN is_dob_correction BOOLEAN DEFAULT FALSE;
ALTER TABLE supervisor_modification_form ADD COLUMN is_dob_correction BOOLEAN DEFAULT FALSE;

/*Pankaj: 30 MARCH 2018: added column*/
ALTER TABLE wireman_examination_form ADD COLUMN total_experience VARCHAR(50) DEFAULT 0;
ALTER TABLE wireman_exemption_form ADD COLUMN total_experience VARCHAR(50) DEFAULT 0;
ALTER TABLE supervisor_examination_form ADD COLUMN total_experience VARCHAR(50) DEFAULT 0;
ALTER TABLE experience ADD COLUMN is_current BOOLEAN DEFAULT FALSE;

/*Pankaj: 31 MARCH 2018: added column*/
ALTER TABLE wireman_renewal_form ADD COLUMN from_date TIMESTAMP;
ALTER TABLE wireman_renewal_form ADD COLUMN to_date TIMESTAMP;
ALTER TABLE supervisor_renewal_form ADD COLUMN from_date TIMESTAMP;
ALTER TABLE supervisor_renewal_form ADD COLUMN to_date TIMESTAMP;
ALTER TABLE inter_state_permit_form ADD COLUMN name_and_address_auth VARCHAR(255) DEFAULT NULL ;

/*Pankaj: 04 April 2018: added column*/
ALTER TABLE wireman_exemption_form DROP column total_experience;
ALTER TABLE supervisor_exemption_form ADD COLUMN total_experience VARCHAR(50) DEFAULT 0;

/*Ravi: 04April 2018: added column */
ALTER TABLE supervisor_examination_form ADD COLUMN whether_wireman_permit VARCHAR(50);

ALTER TABLE form_address ADD COLUMN branch_email VARCHAR(50);
ALTER TABLE form_address ADD COLUMN branch_contact_no VARCHAR(50);
ALTER TABLE form_address ADD COLUMN branch_website VARCHAR(50);

ALTER TABLE form_address ADD COLUMN business_email VARCHAR(50);
ALTER TABLE form_address ADD COLUMN business_contact_no VARCHAR(50);
ALTER TABLE form_address ADD COLUMN business_website VARCHAR(50);

ALTER TABLE em_agency_license DROP column branch_email;
ALTER TABLE em_agency_license DROP column business_email;
ALTER TABLE em_agency_license DROP column branch_contact_no;
ALTER TABLE em_agency_license DROP column business_contact_no;
ALTER TABLE em_agency_license DROP column branch_website;
ALTER TABLE em_agency_license DROP column business_website;

ALTER TABLE om_agency_license DROP column branch_email;
ALTER TABLE om_agency_license DROP column business_email;
ALTER TABLE om_agency_license DROP column branch_contact_no;
ALTER TABLE om_agency_license DROP column business_contact_no;
ALTER TABLE om_agency_license DROP column branch_website;
ALTER TABLE om_agency_license DROP column business_website;

ALTER TABLE it_agency_license DROP column branch_email;
ALTER TABLE it_agency_license DROP column business_email;
ALTER TABLE it_agency_license DROP column branch_contact_no;
ALTER TABLE it_agency_license DROP column business_contact_no;
ALTER TABLE it_agency_license DROP column branch_website;
ALTER TABLE it_agency_license DROP column business_website;