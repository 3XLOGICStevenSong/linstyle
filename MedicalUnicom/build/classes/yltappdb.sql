/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/7/28 14:49:28                           */
/*==============================================================*/


drop table if exists ADMIN_LOGIN;

drop table if exists ADMIN_ROLE;

drop table if exists APPOINT_INQUIRY;

drop table if exists CODE_BASICCLASS;

drop table if exists CODE_DIRECLASS;

drop table if exists CODE_DIRECTION;

drop table if exists CODE_DRUGCLASS;

drop table if exists CODE_DRUGPACK;

drop table if exists CODE_FREQUENCY;

drop table if exists CODE_ITEM;

drop table if exists CODE_PHARMIC;

drop table if exists DEPARTMENT;

drop table if exists DEPARTMENT_DOCTOR;

drop table if exists DEPARTMENT_SYMPTOM;

drop table if exists DEPARTMET_CLASS;

drop table if exists DOCTOR;

drop table if exists DOCTOR_COMMENT;

drop table if exists DOCTOR_SCHEDULE;

drop table if exists FRIEND;

drop table if exists INTERROGATION_PACKAGE;

drop table if exists ORDER_NUMBER;

drop table if exists PATIENT;

drop table if exists PURCHASE_HISTORY;

drop table if exists QA_TYPE;

drop table if exists RECORDS;

drop table if exists ROLE;

drop table if exists SYMPTOM;

drop table if exists USER_LOGIN;

/*==============================================================*/
/* Table: ADMIN_LOGIN                                           */
/*==============================================================*/
create table ADMIN_LOGIN
(
   ADMIN__ID            int(11) not null auto_increment,
   ADMIN_TEL            varchar(20) not null,
   PASSWORD             varchar(40) not null comment '用MD5加密，输入最少六位',
   STATUS               char(1) comment '0：不可用1：可用',
   CREATE_TIME          datetime,
   UPDATE_TIME          datetime,
   primary key (ADMIN__ID)
);

alter table ADMIN_LOGIN comment '后台管理管理员登录表';

/*==============================================================*/
/* Table: ADMIN_ROLE                                            */
/*==============================================================*/
create table ADMIN_ROLE
(
   ID                   int(11) not null auto_increment,
   ROLE_ID              int(11),
   ADMIN__ID            int(11),
   ADMIN_ID             int(11),
   STATU                char(1),
   CREATE_TIME          datetime,
   UPDATE_TIME          datetime,
   primary key (ID)
);

alter table ADMIN_ROLE comment '管理员角色关联表';

/*==============================================================*/
/* Table: APPOINT_INQUIRY                                       */
/*==============================================================*/
create table APPOINT_INQUIRY
(
   APPOINT_ID           int(11) not null,
   PATIENT_ID           int(11) not null,
   DOCTOR_ID            int(11),
   PACKAGE_ID           int(11),
   DEP_CLASS_ID         int(11),
   DEP_ID               int(11),
   SYMPTON_ID_LIST      varchar(50),
   CREATE_TIME          datetime,
   UPDATE_TIME          datetime,
   primary key (APPOINT_ID)
);

alter table APPOINT_INQUIRY comment '预约就诊表';

/*==============================================================*/
/* Table: CODE_BASICCLASS                                       */
/*==============================================================*/
create table CODE_BASICCLASS
(
   CLS_CODE             char(4) not null,
   CLS_NAME             varchar(40) not null,
   primary key (CLS_CODE)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基础分类代码表';

/*==============================================================*/
/* Table: CODE_DIRECLASS                                        */
/*==============================================================*/
create table CODE_DIRECLASS
(
   CLS_CODE             char(4) not null,
   CLS_NAME             varchar(40) not null,
   PRINT_FLAG           char(1) not null,
   primary key (CLS_CODE)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用法分类代码表';

/*==============================================================*/
/* Table: CODE_DIRECTION                                        */
/*==============================================================*/
create table CODE_DIRECTION
(
   DIRE_CODE            varchar(20) not null,
   CLS_CODE             char(4),
   DIRE_NAME            varchar(40) not null,
   DIRE_CLS             char(4) default NULL,
   TYPE                 char(1) not null,
   primary key (DIRE_CODE)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用法代码表';

/*==============================================================*/
/* Table: CODE_DRUGCLASS                                        */
/*==============================================================*/
create table CODE_DRUGCLASS
(
   CLS_CODE             char(4) not null,
   CLS_NAME             varchar(40) not null,
   PARENT               char(4) not null,
   GRADE                decimal(1,0) not null,
   LEAF_FLAG            char(1) not null,
   primary key (CLS_CODE)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='药品类别代码表';

/*==============================================================*/
/* Table: CODE_DRUGPACK                                         */
/*==============================================================*/
create table CODE_DRUGPACK
(
   PACK_CODE            char(4) not null,
   PACK_NAME            varchar(40) not null,
   CLASS                char(1) not null,
   primary key (PACK_CODE)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='剂型代码表';

/*==============================================================*/
/* Table: CODE_FREQUENCY                                        */
/*==============================================================*/
create table CODE_FREQUENCY
(
   FREQ_CODE            char(4) not null,
   FREQ_NAME            varchar(40) default NULL,
   TIMES                decimal(3,0) default NULL,
   CYC                  decimal(3,0) default NULL,
   primary key (FREQ_CODE)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='次数代码表';

/*==============================================================*/
/* Table: CODE_ITEM                                             */
/*==============================================================*/
create table CODE_ITEM
(
   ITEM_CODE            varchar(20) not null,
   ITEM_NAME            varchar(64) not null,
   ITEM_CLS             varchar(1) not null,
   GB_CODE              varchar(20) default NULL,
   MC_CODE              varchar(20) default NULL,
   PY_CODE              varchar(10) default NULL,
   WB_CODE              varchar(10) default NULL,
   LA_CODE              varchar(10) default NULL,
   BAR_CODE             varchar(20) default NULL,
   BASE_CLASS           char(4) default NULL,
   PH_CODE              char(4) default NULL,
   DRUG_CLS             char(4) default NULL,
   PACK_CODE            char(4) default NULL,
   USE_CODE             char(4) default NULL,
   FREQ_CODE            char(4) default NULL,
   STANDARD             varchar(40) default NULL,
   SMALL_UNIT           varchar(16) default NULL,
   BIG_UNIT             varchar(16) default NULL,
   PACK_RATE            decimal(10,4) default NULL,
   STORE_RATE           decimal(10,4) default NULL,
   BASE_DOS             decimal(10,4) default NULL,
   DOS_UNIT             varchar(16) default NULL,
   MAX_DOS              decimal(10,4) default NULL,
   EXEC_DEPT            varchar(20) default NULL,
   DEPT_TYPE            varchar(1) default NULL,
   SOUR_FLAG            varchar(1) default NULL,
   EXP_FLAG             char(1) not null,
   MC_FLAG              char(1) not null,
   SP_FLAG              char(1) not null,
   TEST_FLAG            char(1) not null,
   PRICE_FLAG           char(1) not null,
   PACK_FLAG            char(1) not null,
   MD_FLAG              char(1) not null,
   TOX_FLAG             char(1) not null,
   NAR_FLAG             char(1) not null,
   CON_FLAG             char(1) not null,
   RAD_FLAG             char(1) not null,
   MAK_FLAG             char(1) not null,
   DISABLED             char(1) not null,
   primary key (ITEM_CODE)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目代码表';

/*==============================================================*/
/* Table: CODE_PHARMIC                                          */
/*==============================================================*/
create table CODE_PHARMIC
(
   PH_CODE              char(4) not null,
   PH_NAME              varchar(40) not null,
   PARENT               char(4) not null,
   GRADE                decimal(1,0) not null,
   LEAF_FLAG            char(1) not null,
   primary key (PH_CODE)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='药理分类代码表';

/*==============================================================*/
/* Table: DEPARTMENT                                            */
/*==============================================================*/
create table DEPARTMENT
(
   DEP_ID               int(11) not null,
   DEP_NAME             varchar(40) not null,
   DC_ID                int(11),
   STATUS_FLG           char(1) comment '0:不可用1：可用',
   CREATE_TIME          datetime,
   UPDATE_TIME          datetime,
   primary key (DEP_ID)
);

alter table DEPARTMENT comment '科室管理表';

/*==============================================================*/
/* Table: DEPARTMENT_DOCTOR                                     */
/*==============================================================*/
create table DEPARTMENT_DOCTOR
(
   ID                   int(11) not null auto_increment,
   DEPARTMENT_ID        int(11) not null,
   DOCTOR_ID            int(11) not null,
   CREATE_TIME          datetime,
   UPDATE_TIME          datetime,
   primary key (ID)
);

alter table DEPARTMENT_DOCTOR comment '科室医生关联表';

/*==============================================================*/
/* Table: DEPARTMENT_SYMPTOM                                    */
/*==============================================================*/
create table DEPARTMENT_SYMPTOM
(
   ID                   int(11) not null,
   DEPARTMENT_ID        int(11),
   SYMPTOM_ID           int(11),
   CREATE_TIME          datetime,
   UPDATE_TIME          datetime,
   primary key (ID)
);

alter table DEPARTMENT_SYMPTOM comment '科室症状关联表';

/*==============================================================*/
/* Table: DEPARTMET_CLASS                                       */
/*==============================================================*/
create table DEPARTMET_CLASS
(
   DC_ID                int(11) not null auto_increment,
   DC_NAME              varchar(40),
   STATUS_FLG           char(1) comment '0:不可用1：可用',
   CREATE_TIME          datetime,
   UPDATE_TIME          datetime,
   primary key (DC_ID)
);

alter table DEPARTMET_CLASS comment '科室类别总表';

/*==============================================================*/
/* Table: DOCTOR                                                */
/*==============================================================*/
create table DOCTOR
(
   DOCTOR_ID            int(11) not null auto_increment,
   USER_ID              int(11),
   NAME                 varchar(40),
   SEX                  char(1),
   AGE                  int(3),
   POSITIONAL           varchar(40),
   CARD_NUM             varchar(20) not null,
   CERTIFICATE_NUM      varchar(30) not null,
   CERTIFICATE_PIC      varchar(50) not null,
   HEAD_PIC             varchar(50),
   HOSPITAL_NAME        varchar(40),
   INTRODUCTION         varchar(500),
   VERIFY_FLG           char(1) comment '0:未审核1：审核通过2:审核未通过',
   STATUS_FLG           char(1),
   BANK_OWNER           varchar(40),
   BANK_NUM             varchar(30),
   BANK_NAME            varchar(30),
   HEAL_DISEASE         varchar(200),
   EMAIL                varchar(50),
   CREATE_TIME          datetime,
   UPDATE_TIME          datetime,
   SERVICE_COUNT        int(11),
   GRADE                char(2),
   primary key (DOCTOR_ID)
);

alter table DOCTOR comment '医生管理表';

/*==============================================================*/
/* Table: DOCTOR_COMMENT                                        */
/*==============================================================*/
create table DOCTOR_COMMENT
(
   COMMENT_ID           int(11) not null auto_increment,
   DOCTOR_ID            int(11),
   PATIENT_ID           int(11),
   RECORDS_ID           int(11),
   GRADE                float comment '几颗星',
   CONTENT              varchar(500),
   DELETE_FLG           char(1),
   EVALATION_TYPE       char(1) default '1' comment '1好评2中评3差评',
   EVALATION_TIME       datetime,
   ANONYMOUS_FLAG       char(1) comment '0：不匿名 1：匿名',
   CREATE_TIME          datetime,
   UPDATE_TIME          datetime,
   primary key (COMMENT_ID)
);

alter table DOCTOR_COMMENT comment '医生评价表';

/*==============================================================*/
/* Table: DOCTOR_SCHEDULE                                       */
/*==============================================================*/
create table DOCTOR_SCHEDULE
(
   SCHEDULE_ID          int(11) not null auto_increment,
   DOCTOR_ID            int(11),
   START_DATE           datetime,
   START_TIME           time,
   TIME_INTERVAL        varchar(15) comment '暂时定为15分钟',
   APPOINT_ID           char(1) default '0' comment '0：没被预约1被预约',
   PATIENT_ID           int(11),
   STATUS               char(1) comment '0不可用1:可用',
   CREATE_TIME          datetime,
   UPDATE_TIEME         datetime,
   primary key (SCHEDULE_ID)
);

alter table DOCTOR_SCHEDULE comment '医生日程管理表';

/*==============================================================*/
/* Table: FRIEND                                                */
/*==============================================================*/
create table FRIEND
(
   ID                   int(11) not null auto_increment,
   APPLY_ID             integer(11),
   AGREE_ID             integer(11),
   CREATE_TIME          datetime,
   STATUS               char(1),
   primary key (ID)
);

alter table FRIEND comment '好友管理表';

/*==============================================================*/
/* Table: INTERROGATION_PACKAGE                                 */
/*==============================================================*/
create table INTERROGATION_PACKAGE
(
   PACKAGE_ID           int(11) not null auto_increment,
   NAME                 varchar(50) not null,
   DOCTOR_ID            int(11) not null,
   COUNT                int(5) comment '5',
   UNIT                 varchar(5) comment '次',
   QA_TIME              varchar(20) comment '1',
   TIME_UNIT            varchar(20) comment '小时',
   CREATE_TIME          datetime,
   UPDATE_TIEM          datetime,
   TYPE_ID              char(1),
   TEL_COUNT            tinyint,
   TEL_UNIT             varchar(10),
   TOTAL                decimal(6) comment '可加密',
   EFFECT_TIME          datetime,
   TEL_TIME2            varchar(20) comment '30',
   TEL_UNIT2            varchar(20) comment '分钟',
   primary key (PACKAGE_ID)
);

alter table INTERROGATION_PACKAGE comment '问诊套餐管理表';

/*==============================================================*/
/* Table: ORDER_NUMBER                                          */
/*==============================================================*/
create table ORDER_NUMBER
(
   ID                   bigint not null,
   CREATE_TIME          datetime,
   UPDATE_TIME          datetime,
   primary key (ID)
);

alter table ORDER_NUMBER comment '订单号生成表';

/*==============================================================*/
/* Table: PATIENT                                               */
/*==============================================================*/
create table PATIENT
(
   PATIENT_ID           int(11) not null,
   NAME                 varchar(40),
   BIRTH                datetime,
   AGE_UNIT             char(1) comment 'Y岁 M月 D天',
   SEX                  char(1),
   MARRY                char(1) comment '1已婚 0未婚',
   ADDRESS              varchar(100),
   BT                   char(2),
   USER_ID              int(11) not null,
   MEDICAL_EATEN_HISTORY varchar(1000),
   ALLERGY_HISTORY      varchar(1000),
   ILLNESS_HISTORY      varchar(1000),
   AMT                  varchar(64) comment '需要加密',
   PATIENT_PIC          varchar(100),
   EMAIL                varchar(50),
   CREATE_TIME          datetime,
   UPDATE_TIME          datetime,
   primary key (PATIENT_ID)
);

alter table PATIENT comment '患者管理表';

/*==============================================================*/
/* Table: PURCHASE_HISTORY                                      */
/*==============================================================*/
create table PURCHASE_HISTORY
(
   BUY_ID               int(11) not null auto_increment,
   PATIENT_ID           int(11),
   PACKAGE_ID           int(11),
   BUY_TOTAL            decimal(5,0),
   BUY_HASH             varchar(64),
   PAY_STATUS           char(1) comment '0：未支付1：支付  2:消费',
   PAY_TYPE             char(1) comment '0：支付宝1：微信支付2：银联支付3：医保支付',
   PAY_TIME             datetime,
   STATUS               char(1),
   BUY_NUM              bigint,
   BUY_TIME             datetime,
   UPDATE_TIME          datetime,
   primary key (BUY_ID)
);

alter table PURCHASE_HISTORY comment '购买记录管理表';

/*==============================================================*/
/* Table: QA_TYPE                                               */
/*==============================================================*/
create table QA_TYPE
(
   QA_TYPE_ID           int(11) not null auto_increment,
   QA_TYPE_NAME         varchar(45) not null,
   STATUS               char(1) comment '0:不可用1：可用',
   CREATE_TIME          datetime,
   UPDATE_TIME          datetime,
   primary key (QA_TYPE_ID)
);

alter table QA_TYPE comment '问诊类型管理表';

/*==============================================================*/
/* Table: RECORDS                                               */
/*==============================================================*/
create table RECORDS
(
   APPOINT_ID           int(11) not null,
   DEP_CLASS_ID         int(11),
   RESULT               varchar(1000),
   GUIDANCE             varchar(1000),
   ANALYSIS             varchar(1000),
   INQUIRY_STATUS       char(1) comment '0：初诊1:没有就诊结果2：就诊结束',
   SYMPTON_DESCRIBE     varchar(500),
   DOCTOR_MEMO          varchar(500),
   APPOINT_TIME         datetime,
   START_TIME           datetime,
   END_TIME             datetime,
   COMMENT_CONTENT      varchar(500),
   COMMENT_GRADE        float,
   ROLE                 char(1) comment '0:医生1：患者',
   RECORDS_TYPE         char(1) comment '0：视频1：电话...',
   CREATE_TIME          datetime,
   UPDATE_TIME          datetime,
   primary key (APPOINT_ID)
);

/*==============================================================*/
/* Table: ROLE                                                  */
/*==============================================================*/
create table ROLE
(
   ROLE_ID              int(11) not null auto_increment,
   ROLE_NAME            varchar(50) not null,
   STATU                char(1),
   CREATE_TIME          datetime,
   UPDATE_TIME          datetime,
   primary key (ROLE_ID)
);

alter table ROLE comment '角色管理表';

/*==============================================================*/
/* Table: SYMPTOM                                               */
/*==============================================================*/
create table SYMPTOM
(
   SYMPTOM_ID           int(11) not null,
   SYMPTOM_NAME         varchar(40) not null,
   STATUS_FLG           char(1) comment '0:不可用1：可用',
   MEMO                 varchar(100),
   SYMPTOM_TYPE         varchar(10) comment '暂时用不到',
   PATIENT_SEX          char(1) comment '0:不区分1:男2：女',
   PATIENT_AGE          varchar(20),
   CREATE_TIME          datetime,
   UPDATE_TIME          datetime,
   primary key (SYMPTOM_ID)
);

alter table SYMPTOM comment '症状管理表';

/*==============================================================*/
/* Table: USER_LOGIN                                            */
/*==============================================================*/
create table USER_LOGIN
(
   USER_ID              int(11) not null auto_increment,
   USER_TEL             varchar(20) not null,
   PASSWORD             varchar(40) not null comment '用MD5加密，输入最少六位',
   ROLE                 char(1) not null comment '0：医生1：患者',
   CREATE_TIME          datetime,
   UPDATE_TIME          datetime,
   STATUS               char(1) comment '0：不可用1：可用',
   primary key (USER_ID)
);

alter table USER_LOGIN comment '用户登录管理表';

alter table ADMIN_ROLE add constraint FK_Reference_24 foreign key (ROLE_ID)
      references ROLE (ROLE_ID) on delete restrict on update restrict;

alter table ADMIN_ROLE add constraint FK_Reference_25 foreign key (ADMIN__ID)
      references ADMIN_LOGIN (ADMIN__ID) on delete restrict on update restrict;

alter table APPOINT_INQUIRY add constraint FK_Reference_11 foreign key (DEP_ID)
      references DEPARTMENT_SYMPTOM (ID) on delete restrict on update restrict;

alter table APPOINT_INQUIRY add constraint FK_Reference_32 foreign key (DOCTOR_ID)
      references DOCTOR (DOCTOR_ID) on delete restrict on update restrict;

alter table APPOINT_INQUIRY add constraint FK_Reference_8 foreign key (PATIENT_ID)
      references PATIENT (PATIENT_ID) on delete restrict on update restrict;

alter table CODE_DIRECTION add constraint FK_Reference_21 foreign key (CLS_CODE)
      references CODE_DIRECLASS (CLS_CODE) on delete restrict on update restrict;

alter table CODE_ITEM add constraint FK_Reference_15 foreign key (BASE_CLASS)
      references CODE_BASICCLASS (CLS_CODE) on delete restrict on update restrict;

alter table CODE_ITEM add constraint FK_Reference_16 foreign key (DRUG_CLS)
      references CODE_DRUGCLASS (CLS_CODE) on delete restrict on update restrict;

alter table CODE_ITEM add constraint FK_Reference_17 foreign key (PACK_CODE)
      references CODE_DRUGPACK (PACK_CODE) on delete restrict on update restrict;

alter table CODE_ITEM add constraint FK_Reference_18 foreign key (PH_CODE)
      references CODE_PHARMIC (PH_CODE) on delete restrict on update restrict;

alter table CODE_ITEM add constraint FK_Reference_19 foreign key (FREQ_CODE)
      references CODE_FREQUENCY (FREQ_CODE) on delete restrict on update restrict;

alter table CODE_ITEM add constraint FK_Reference_20 foreign key (USE_CODE)
      references CODE_DIRECTION (DIRE_CODE) on delete restrict on update restrict;

alter table DEPARTMENT add constraint FK_Reference_1 foreign key (DC_ID)
      references DEPARTMET_CLASS (DC_ID) on delete restrict on update restrict;

alter table DEPARTMENT_DOCTOR add constraint FK_Reference_5 foreign key (DOCTOR_ID)
      references DOCTOR (DOCTOR_ID) on delete restrict on update restrict;

alter table DEPARTMENT_DOCTOR add constraint FK_Reference_6 foreign key (DEPARTMENT_ID)
      references DEPARTMENT (DEP_ID) on delete restrict on update restrict;

alter table DEPARTMENT_SYMPTOM add constraint FK_Reference_2 foreign key (DEPARTMENT_ID)
      references DEPARTMENT (DEP_ID) on delete restrict on update restrict;

alter table DEPARTMENT_SYMPTOM add constraint FK_Reference_3 foreign key (SYMPTOM_ID)
      references SYMPTOM (SYMPTOM_ID) on delete restrict on update restrict;

alter table DOCTOR add constraint FK_Reference_4 foreign key (USER_ID)
      references USER_LOGIN (USER_ID) on delete restrict on update restrict;

alter table DOCTOR_COMMENT add constraint FK_Reference_29 foreign key (DOCTOR_ID)
      references DOCTOR (DOCTOR_ID) on delete restrict on update restrict;

alter table DOCTOR_COMMENT add constraint FK_Reference_30 foreign key (PATIENT_ID)
      references PATIENT (PATIENT_ID) on delete restrict on update restrict;

alter table DOCTOR_COMMENT add constraint FK_Reference_31 foreign key (RECORDS_ID)
      references RECORDS (APPOINT_ID) on delete restrict on update restrict;

alter table FRIEND add constraint FK_Reference_13 foreign key (APPLY_ID)
      references USER_LOGIN (USER_ID) on delete restrict on update restrict;

alter table FRIEND add constraint FK_Reference_14 foreign key (AGREE_ID)
      references USER_LOGIN (USER_ID) on delete restrict on update restrict;

alter table INTERROGATION_PACKAGE add constraint FK_Reference_22 foreign key (DOCTOR_ID)
      references DOCTOR (DOCTOR_ID) on delete restrict on update restrict;

alter table INTERROGATION_PACKAGE add constraint FK_Reference_28 foreign key (TYPE_ID)
      references QA_TYPE (QA_TYPE_ID) on delete restrict on update restrict;

alter table PATIENT add constraint FK_Reference_7 foreign key (USER_ID)
      references USER_LOGIN (USER_ID) on delete restrict on update restrict;

alter table PURCHASE_HISTORY add constraint FK_Reference_10 foreign key (PATIENT_ID)
      references PATIENT (PATIENT_ID) on delete restrict on update restrict;

alter table PURCHASE_HISTORY add constraint FK_Reference_26 foreign key (PACKAGE_ID)
      references INTERROGATION_PACKAGE (PACKAGE_ID) on delete restrict on update restrict;

alter table RECORDS add constraint FK_Reference_27 foreign key (DEP_CLASS_ID)
      references APPOINT_INQUIRY (APPOINT_ID) on delete restrict on update restrict;

