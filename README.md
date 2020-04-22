# mybatisXmlToSql
将mybatis自动生产xml转换成Sql文件
项目使用java gui实现
目前只能简单xml：
<mapper namespace="com.cskaoyan.mapper.CustomMapper">

    <resultMap id="BaseResultMap" type="com.cskaoyan.pojo.Custom">
        <id column="custom_id" jdbcType="VARCHAR" property="customId">
        <result column="custom_name" jdbcType="VARCHAR" property="customName">
        <result column="full_name" jdbcType="VARCHAR" property="fullName">
        <result column="address" jdbcType="VARCHAR" property="address">
        <result column="fax" jdbcType="VARCHAR" property="fax">
        <result column="email" jdbcType="VARCHAR" property="email">
        <result column="owner_name" jdbcType="VARCHAR" property="ownerName">
        <result column="owner_tel" jdbcType="VARCHAR" property="ownerTel">
        <result column="status" jdbcType="INTEGER" property="status">
        <result column="note" jdbcType="VARCHAR" property="note">
    < resultMap>
 
	
<mapper>

转换成sql：

CREATE TABLE Custom( custom_id VARCHAR(255),

custom_name VARCHAR(255),

full_name VARCHAR(255),

address VARCHAR(255),

fax VARCHAR(255),

email VARCHAR(255),

owner_name VARCHAR(255),

owner_tel VARCHAR(255),

status INTEGER,

note VARCHAR(255) 

 )
 
 只运行 JFrameMain：
 然后选择xml 存放目录，选择转换sql存放目录，点击开始即可。
 
 
