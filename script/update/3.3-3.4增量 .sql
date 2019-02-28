ALTER TABLE uk_xiaoe_kbs_type ADD parentid varchar(32) DEFAULT '0';
ALTER TABLE uk_xiaoe_scene_type ADD parentid varchar(32) DEFAULT '0';


ALTER TABLE uk_xiaoe_kbs_type ADD typeid varchar(32);
ALTER TABLE uk_xiaoe_scene_type ADD typeid varchar(32);

update uk_xiaoe_kbs_type set typeid = id ;
update uk_xiaoe_scene_type set typeid = id ;

ALTER TABLE uk_xiaoe_topic ADD silimar text COMMENT '¿‡À∆Œ Ã‚';