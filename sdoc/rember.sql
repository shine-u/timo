create table sys_dept
(
    id          bigint auto_increment comment '主键ID'
        primary key,
    title       varchar(255) null comment '部门名称',
    pid         bigint       null comment '父级ID',
    pids        varchar(255) null comment '所有父级编号',
    sort        int          null comment '排序',
    remark      varchar(255) null comment '备注',
    create_date datetime     null comment '创建时间',
    update_date datetime     null comment '更新时间',
    create_by   bigint       null comment '创建用户',
    update_by   bigint       null comment '更新用户',
    status      tinyint      null comment '状态（-1:删除,0:冻结,1:正常）'
)
    charset = utf8;

create table sys_user
(
    id          bigint auto_increment comment '主键ID'
        primary key,
    username    varchar(255) null comment '用户名',
    nickname    varchar(255) null comment '用户昵称',
    password    char(64)     null comment '密码',
    salt        varchar(255) null comment '密码盐',
    dept_id     bigint       null comment '部门ID',
    picture     varchar(255) null comment '头像',
    sex         tinyint      null comment '性别（1:男,2:女）',
    email       varchar(255) null comment '邮箱',
    phone       varchar(255) null comment '电话号码',
    remark      varchar(255) null comment '备注',
    create_date datetime     null comment '创建时间',
    update_date datetime     null comment '更新时间',
    status      tinyint      null comment '状态（-1:删除,0:冻结,1:正常）',
    constraint FKb3pkx0wbo6o8i8lj0gxr37v1n
        foreign key (dept_id) references sys_dept (id)
)
    charset = utf8;

create table sys_action_log
(
    id          bigint auto_increment comment '主键ID'
        primary key,
    name        varchar(255) null comment '日志名称',
    type        tinyint      null comment '日志类型',
    ipaddr      varchar(255) null comment '操作IP地址',
    clazz       varchar(255) null comment '产生日志的类',
    method      varchar(255) null comment '产生日志的方法',
    model       varchar(255) null comment '产生日志的表',
    record_id   bigint       null comment '产生日志的数据id',
    message     text         null comment '日志消息',
    create_date datetime     null comment '记录时间',
    oper_name   varchar(255) null comment '产生日志的用户昵称',
    oper_by     bigint       null comment '产生日志的用户',
    constraint FK32gm4dja0jetx58r9dc2uljiu
        foreign key (oper_by) references sys_user (id)
)
    charset = utf8;

alter table sys_dept
    add constraint FK83g45s1cjqqfpifhulqhv807m
        foreign key (update_by) references sys_user (id);

alter table sys_dept
    add constraint FKifwd1h4ciusl3nnxrpfpv316u
        foreign key (create_by) references sys_user (id);

create table sys_dict
(
    id          bigint auto_increment comment '主键ID'
        primary key,
    title       varchar(255) null comment '字典名称',
    name        varchar(255) null comment '字典键名',
    type        tinyint      null comment '字典类型',
    value       text         null comment '字典键值',
    remark      varchar(255) null comment '备注',
    create_date datetime     null comment '创建时间',
    update_date datetime     null comment '更新时间',
    create_by   bigint       null comment '创建用户',
    update_by   bigint       null comment '更新用户',
    status      tinyint      null comment '状态（-1:删除,0:冻结,1:正常）',
    constraint FKag4shuprf2tjot9i1mhh37kk6
        foreign key (create_by) references sys_user (id),
    constraint FKoyng5jlifhsme0gc1lwiub0lr
        foreign key (update_by) references sys_user (id)
)
    charset = utf8;

create table sys_file
(
    id          bigint auto_increment comment '主键ID'
        primary key,
    name        varchar(255) null comment '文件名',
    path        varchar(255) null comment '文件存放路径',
    url         varchar(255) null comment '文件获取路径',
    type        varchar(255) null comment 'MIME文件类型',
    size        bigint       null comment '文件大小',
    md5         varchar(255) null comment 'MD5值',
    sha1        varchar(255) null comment 'SHA1值',
    status      int          null comment '状态（-1删除，0禁用，1正常）',
    create_by   bigint       null comment '上传者',
    create_time datetime     null comment '修改时间',
    update_time datetime     null comment '修改时间',
    constraint FKkkles8yp0a156p4247cc22ovn
        foreign key (create_by) references sys_user (id)
)
    charset = utf8;

create table sys_menu
(
    id          bigint auto_increment comment '主键ID'
        primary key,
    title       varchar(255) null comment '菜单名称',
    pid         bigint       null comment '父级编号',
    pids        varchar(255) null comment '所有父级编号',
    url         varchar(255) null comment 'URL地址',
    perms       varchar(255) null comment '权限标识',
    icon        varchar(255) null comment '图标',
    type        tinyint      null comment '类型（1:一级菜单,2:子级菜单,3:不是菜单）',
    sort        int          null comment '排序',
    remark      varchar(255) null comment '备注',
    create_date datetime     null comment '创建时间',
    update_date datetime     null comment '更新时间',
    create_by   bigint       null comment '创建用户',
    update_by   bigint       null comment '更新用户',
    status      tinyint      null comment '状态（-1:删除,0:冻结,1:正常）',
    constraint FKoxg2hi96yr9pf2m0stjomr3we
        foreign key (create_by) references sys_user (id),
    constraint FKsiko0qcr8ddamvrxf1tk4opgc
        foreign key (update_by) references sys_user (id)
)
    charset = utf8;

create table sys_role
(
    id          bigint auto_increment comment '主键ID'
        primary key,
    title       varchar(255) null comment '角色名称（中文名）',
    name        varchar(255) null comment '标识名称',
    remark      varchar(255) null comment '备注',
    create_date datetime     null comment '创建时间',
    update_date datetime     null comment '更新时间',
    create_by   bigint       null comment '创建用户',
    update_by   bigint       null comment '更新用户',
    status      tinyint      null comment '状态（-1:删除,0:冻结,1:正常）',
    constraint FKdkwvv0rm6j3d5l6hwsy2dplol
        foreign key (create_by) references sys_user (id),
    constraint FKrouqqi3f2bgc5o83wdstlh6q4
        foreign key (update_by) references sys_user (id)
)
    charset = utf8;

create table sys_role_menu
(
    role_id bigint not null,
    menu_id bigint not null,
    primary key (role_id, menu_id),
    constraint FKf3mud4qoc7ayew8nml4plkevo
        foreign key (menu_id) references sys_menu (id),
    constraint FKkeitxsgxwayackgqllio4ohn5
        foreign key (role_id) references sys_role (id)
)
    charset = utf8;

create table sys_user_role
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    constraint FKb40xxfch70f5qnyfw8yme1n1s
        foreign key (user_id) references sys_user (id),
    constraint FKhh52n8vd4ny9ff4x9fb8v65qx
        foreign key (role_id) references sys_role (id)
)
    charset = utf8;

create table tb_user
(
    id          bigint auto_increment comment '用户id'
        primary key,
    account     varchar(32) charset utf8  not null comment '账号',
    password    varchar(255) charset utf8 not null comment '密码加密',
    phone       bigint                    null comment '电话',
    email       varchar(64) charset utf8  null comment '邮箱',
    address     varchar(128) charset utf8 null comment '地址',
    ip          varchar(32) charset utf8  null comment 'IP地址',
    img_url     varchar(128) charset utf8 null comment '头像地址',
    login_count int default 1             null comment '登录次数',
    status      int                       null comment '状态（-1删除，0禁用，1正常）',
    create_date datetime                  null comment '创建时间',
    update_date datetime                  null comment '修改时间(最后登录时间)'
)
    comment '用户表';

create table tb_post
(
    id          bigint auto_increment comment '主键'
        primary key,
    post_title  varchar(64) not null comment '帖子标题',
    content     text        not null comment '内容',
    create_by   bigint      not null comment '创建者',
    update_by   bigint      not null comment '修改者',
    status      int         null comment '状态（-1删除，0禁用，1正常）',
    create_date datetime    null comment '创建时间',
    update_date datetime    null comment '修改时间',
    constraint FKfa899no3yatxfqegjcdtne14f
        foreign key (create_by) references sys_user (id),
    constraint FKhnbcnjwveq4lwo22pf9ayi9w5
        foreign key (update_by) references sys_user (id),
    constraint FKis199ikjqwuc8mn4nrcl2exc
        foreign key (create_by) references tb_user (id),
    constraint FKnq3hr2he1mho59wdnh3m0gxfr
        foreign key (update_by) references tb_user (id)
)
    comment '帖子表' charset = utf8;

