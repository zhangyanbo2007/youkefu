ALTER TABLE uk_callcenter_event ADD SATISF tinyint DEFAULT false;
ALTER TABLE uk_callcenter_event ADD SATISFACTION varchar(50);
ALTER TABLE uk_callcenter_event ADD SATISFDATE datetime;

update uk_callcenter_event set SATISF = false ;


ALTER TABLE uk_callcenter_extention ADD subtype varchar(50);

ALTER TABLE uk_workorders ADD frommobile tinyint DEFAULT 0;

ALTER TABLE uk_kbs_topic ADD frommobile tinyint DEFAULT 0;


ALTER TABLE uk_xiaoe_scene ADD frommobile tinyint DEFAULT 0;