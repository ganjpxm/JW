/*==============================================================*/
/* Table: am_user                                               */
/*==============================================================*/
create table am_user
(
   user_id              char(32) not null,
   user_cd              varchar(32),
   user_name            varchar(30),
   user_alias           varchar(30),
   user_level           numeric(9,0) default 0,
   user_score           numeric(9,0) default 0,
   login_times          numeric(9,0) default 0,
   password             varchar(128),
   mobile_phone         varchar(20),
   qq                   varchar(15),
   email                varchar(64),
   birthday             date,
   photo_url            varchar(256),
   operator_id          char(32),
   operator_name        varchar(30),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: am_user_detail                                        */
/*==============================================================*/
create table am_user_detail
(
   user_detail_id       char(32) not null,
   user_id              char(32),
   home_phone           varchar(20),
   country_id           char(32),
   province_id          char(32),
   city_id              char(32),
   birth_place          varchar(255),
   live_place           varchar(255),
   annual_income        decimal(24,8) default 0,
   job_category_pid     char(32),
   job_category_id      char(32),
   job_position_id      char(32),
   company_name         varchar(64),
   shool_name           varchar(64),
   education_id         char(32),
   interest_ids         varchar(255),
   password_tip_custom  varchar(50),
   password_tip_id      char(32),
   password_tip_answer  varchar(50),
   signature            varchar(255),
   remark               varchar(255),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (user_detail_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: am_role                                               */
/*==============================================================*/
create table am_role
(
   role_id              char(32) not null,
   role_cd              varchar(32),
   role_name            varchar(64),
   description          varchar(512),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: am_user_role                                          */
/*==============================================================*/
create table am_user_role
(
   user_role_id         char(32) not null,
   role_id              char(32),
   user_id              char(32),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (user_role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: bm_config                                             */
/*==============================================================*/
create table bm_config
(
   config_id            char(32) not null,
   config_cd            varchar(32),
   config_name          varchar(64),
   config_value         varchar(256),
   description          varchar(512),
   lang                 char(5),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (config_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: bm_param                                              */
/*==============================================================*/
create table bm_param
(
   param_id             char(32) not null,
   param_cd             varchar(32),
   param_name           varchar(64),
   param_type_cd        varchar(32),
   param_type_name      varchar(64),
   display_no           numeric(9,0) default 0,
   lang                 char(5),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (param_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: bm_menu                                               */
/*==============================================================*/
create table bm_menu
(
   menu_id              char(32) not null,
   menu_pid             char(32),
   menu_cd              varchar(32),
   menu_name            varchar(256),
   image_url            varchar(256),
   url                  varchar(512),
   display_level        numeric(9,0) default 0,
   display_no           numeric(9,0) default 0,
   end_flag             char(1) comment '0:no, 1:yes',
   menu_category_id     char(32),
   lang                 char(5),
   role_ids             varchar(320),
   modify_timestamp     timestamp,
   create_date_time     datetime,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (menu_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: am_role_menu                                          */
/*==============================================================*/
create table am_role_menu
(
   role_menu_id         char(32) not null,
   menu_id              char(32),
   role_id              char(32),
   create_date_time     datetime,
   data_state           char(1) comment '0：normal, 1：delete',
   modify_timestamp     timestamp,
   primary key (role_menu_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: cm_category                                           */
/*==============================================================*/
create table cm_category
(
   category_id          char(32) not null,
   category_pid         char(32),
   category_cd          varchar(32),
   category_name        varchar(64),
   end_flag             char(1) comment '0:no, 1:yes',
   display_no           numeric(9,0) default 0,
   lang                 char(5),
   role_ids             varchar(320),
   operator_id          char(32),
   operator_name        varchar(30),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: cm_photo                                              */
/*==============================================================*/
create table cm_photo
(
   photo_id             char(32) not null,
   photo_name           varchar(64),
   ref_article_id       varchar(32),
   title                varchar(128),
   thumb_url            varchar(256),
   url                  varchar(512),
   origin_url           varchar(256),
   tag                  varchar(128),
   display_no           numeric(9,0) default 0,
   remark               varchar(255),
   lang                 char(5),
   role_ids             varchar(320),
   operator_id          char(32),
   operator_name        varchar(30),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (photo_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: cm_photo_category                                     */
/*==============================================================*/
create table cm_photo_category
(
   photo_category_id    char(32) not null,
   photo_id             char(32),
   category_id          char(32),
   default_photo        char(1) comment '0:no,1:yes',
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (photo_category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: cm_article                                            */
/*==============================================================*/
create table cm_article
(
   article_id           char(32) not null,
   article_cd           varchar(32),
   title                varchar(128),
   summary              text,
   content              text,
   author               varchar(64),
   image_url            varchar(256),
   origin_url           varchar(256),
   tag                  varchar(128),
   display_no           numeric(9,0) default 0,
   lang                 char(5),
   role_ids             varchar(320),
   operator_id          char(32),
   operator_name        varchar(30),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (article_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: cm_article_category                                   */
/*==============================================================*/
create table cm_article_category
(
   article_category_id  char(32) not null,
   article_id           char(32),
   category_id          char(32),
   create_date_time     datetime,
   data_state           char(1) comment '0：normal, 1：delete',
   modify_timestamp     timestamp,
   primary key (article_category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: cm_article_remark                                     */
/*==============================================================*/
create table cm_article_remark
(
   article_remark_id    char(32) not null,
   article_id           char(32),
   remark               varchar(255),
   display_no           numeric(9,0) default 0,
   operator_id          char(32),
   operator_name        varchar(30),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (article_remark_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: cm_vocabulary                                         */
/*==============================================================*/
create table cm_vocabulary
(
   vocabulary_id        char(32) not null,
   first_name           varchar(125),
   first_phonogram      varchar(64),
   first_pronounce_url  varchar(256),
   first_mean           varchar(125),
   first_description    varchar(256),
   second_name          varchar(125),
   second_phonogram     varchar(64),
   second_pronounce_url varchar(256),
   second_mean          varchar(125),
   second_description   varchar(256),
   image_url            varchar(256),
   tag                  varchar(128),
   level_id             char(32),
   level_name           varchar(32),
   display_no           numeric(9,0) default 0,
   lang                 char(5),
   role_ids             varchar(320),
   operator_id          char(32),
   operator_name        varchar(30),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (vocabulary_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: cm_vocabulary_article                                 */
/*==============================================================*/
create table cm_vocabulary_article
(
   vocabulary_article_id char(32) not null,
   article_id           char(32),
   vocabulary_id        char(32),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (vocabulary_article_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: cm_vocabulary_category                                */
/*==============================================================*/
create table cm_vocabulary_category
(
   vocabulary_category_id char(32) not null,
   vocabulary_id        char(32),
   category_id          char(32),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (vocabulary_category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: cm_audio                                              */
/*==============================================================*/
create table cm_audio
(
   audio_id             char(32) not null,
   audio_name           varchar(64),
   audio_format         varchar(10),
   title                varchar(128),
   thumb_url            varchar(256),
   url                  varchar(512),
   origin_website       varchar(30),
   origin_url           varchar(256),
   tag                  varchar(128),
   display_no           numeric(9,0) default 0,
   description          varchar(1024),
   remark               varchar(255),
   lang                 char(5),
   role_ids             varchar(320),
   operator_id          char(32),
   operator_name        varchar(30),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (audio_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: cm_video                                              */
/*==============================================================*/
create table cm_video
(
   video_id             char(32) not null,
   video_name           varchar(64),
   video_format         varchar(10),
   title                varchar(128),
   thumb_url            varchar(256),
   url                  varchar(512),
   origin_url           varchar(256),
   origin_website       varchar(30),
   tag                  varchar(128),
   display_no           numeric(9,0) default 0,
   description          varchar(1024),
   remark               varchar(255),
   lang                 char(5),
   role_ids             varchar(320),
   operator_id          char(32),
   operator_name        varchar(30),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (video_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: cm_file                                               */
/*==============================================================*/
create table cm_file
(
   file_id              char(32) not null,
   file_name            varchar(64),
   title                varchar(128),
   thumb_url            varchar(256),
   url                  varchar(512),
   origin_url           varchar(256),
   origin_website       varchar(30),
   tag                  varchar(128),
   display_no           numeric(9,0) default 0,
   description          varchar(1024),
   remark               varchar(255),
   lang                 char(5),
   role_ids             varchar(320),
   operator_id          char(32),
   operator_name        varchar(30),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (file_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: cm_evaluate_item                                      */
/*==============================================================*/
create table cm_evaluate_item
(
   evaluate_item_id     char(32) not null,
   evaluate_table_id    char(32),
   item_name            varchar(512) not null,
   item_position        varchar(32),
   item_value_type      varchar(128),
   item_value_type_id   char(32),
   item_options         varchar(1024),
   item_order           numeric(9,0) default 0,
   item_width           varchar(5),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (evaluate_item_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: cm_evaluate_result                                    */
/*==============================================================*/
create table cm_evaluate_result
(
   evaluate_result_id   char(32) not null,
   evaluate_item_id     char(32),
   table_id             char(32),
   table_type           char(2),
   item_name            varchar(512),
   item_position        varchar(32),
   item_value_type      varchar(128),
   item_value_id        char(32),
   item_value           text,
   item_order           numeric(9,0) default 0,
   item_width           varchar(5),
   person_id            char(32),
   kinsfolk_name        varchar(64),
   evaluate_date        date,
   operator_id          char(32),
   operator_name        varchar(30),
   remark               varchar(255),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (evaluate_result_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: cm_evaluate_table                                     */
/*==============================================================*/
create table cm_evaluate_table
(
   evaluate_table_id    char(32) not null,
   table_cd             varchar(32),
   table_name           varchar(64),
   table_type           char(2),
   start_date           date,
   end_date             date,
   role_ids             varchar(320),
   operator_id          char(32),
   operator_name        varchar(30),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (evaluate_table_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*==============================================================*/
/* Table: cm_user_evaluate_table_result                         */
/*==============================================================*/
create table cm_user_evaluate_table_result
(
   user_evaluate_table_result_id char(32) not null,
   user_id              char(32),
   evaluate_table_id    char(32),
   result               varchar(2048),
   score                decimal(24,2) default 0,
   evaluate_date        date,
   remark               varchar(255),
   user_name            varchar(30),
   operator_id          char(32),
   operator_name        varchar(30),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (user_evaluate_table_result_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: bm_user_login_info                                    */
/*==============================================================*/
create table bm_user_login_info
(
   user_login_info_id   char(32) not null,
   login_user_id        char(32),
   login_user_name      varchar(30),
   login_time           datetime,
   logout_time          datetime,
   device_type          varchar(20),
   device_vender        varchar(20),
   device_series        varchar(20),
   device_version       varchar(20),
   browser_type         varchar(20),
   browser_version      varchar(20),
   platform_type        varchar(20),
   platform_series      varchar(20),
   platform_version     varchar(20),
   ip_address           varchar(23),
   primary key (user_login_info_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: cm_feeback                                            */
/*==============================================================*/
create table cm_feedback
(
   feeback_id           char(32) not null,
   feeback_type         varchar(30),
   section              varchar(40),
   subject              varchar(128),
   content              text,
   operator_id          char(32),
   operator_name        varchar(30),
   create_date_time     datetime,
   modify_timestamp     timestamp,
   data_state           char(1) comment '0：normal, 1：delete',
   primary key (feeback_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
