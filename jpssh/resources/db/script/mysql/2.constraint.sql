create unique index user_cd_index on am_user (user_cd);
create unique index user_email_index on am_user (email);
create unique index first_name_index on cm_vocabulary (first_name);

alter table am_role_menu add constraint FK_me_relate_role_menu foreign key (menu_id)
      references bm_menu (menu_id) on delete restrict on update restrict;
alter table am_role_menu add constraint FK_ro_relate_ro_me foreign key (role_id)
      references am_role (role_id) on delete restrict on update restrict;
alter table am_user_detail add constraint FK_us_relate_us_de foreign key (user_id)
      references am_user (user_id) on delete restrict on update restrict;
alter table am_user_role add constraint FK_ro_to_us_ro foreign key (role_id)
      references am_role (role_id) on delete restrict on update restrict;
alter table am_user_role add constraint FK_us_to_us_ro foreign key (user_id)
      references am_user (user_id) on delete restrict on update restrict;
      
alter table cm_photo_category add constraint FK_ca_relate_ph_ca foreign key (category_id)
      references cm_category (category_id) on delete restrict on update restrict;
alter table cm_photo_category add constraint FK_ph_relate_ph_ca foreign key (photo_id)
      references cm_photo (photo_id) on delete restrict on update restrict;

alter table cm_article_category add constraint FK_ca_relate_ar_ca foreign key (category_id)
      references cm_category (category_id) on delete restrict on update restrict;
alter table cm_article_category add constraint FK_ar_relate_ar_ca foreign key (article_id)
      references cm_article (article_id) on delete restrict on update restrict;
alter table cm_article_remark add constraint FK_ar_relate_ar_re foreign key (article_id)
      references cm_article (article_id) on delete restrict on update restrict;
alter table cm_vocabulary_article add constraint FK_ar_relate_ar_vo foreign key (article_id)
      references cm_article (article_id) on delete restrict on update restrict;
alter table cm_vocabulary_article add constraint FK_vo_relate_vo_ar foreign key (vocabulary_id)
      references cm_vocabulary (vocabulary_id) on delete restrict on update restrict;
alter table cm_vocabulary_category add constraint FK_ca_relate_vo_ca foreign key (category_id)
      references cm_category (category_id) on delete restrict on update restrict;
alter table cm_vocabulary_category add constraint FK_vo_relate_vo_ca foreign key (vocabulary_id)
      references cm_vocabulary (vocabulary_id) on delete restrict on update restrict;      
      
alter table cm_evaluate_item add constraint FK_ev_ta_ralate_ev_it foreign key (evaluate_table_id)
      references cm_evaluate_table (evaluate_table_id) on delete restrict on update restrict;
alter table cm_evaluate_result add constraint FK_ch_ev_relate_ev_it foreign key (evaluate_item_id)
      references cm_evaluate_item (evaluate_item_id) on delete restrict on update restrict;

alter table cm_user_evaluate_table_result add constraint FK_ev_ta_relate_us_ev_ta_re foreign key (evaluate_table_id)
      references cm_evaluate_table (evaluate_table_id) on delete restrict on update restrict;
alter table cm_user_evaluate_table_result add constraint FK_us_relate_us_ev_ta_re foreign key (user_id)
      references am_user (user_id) on delete restrict on update restrict;
      