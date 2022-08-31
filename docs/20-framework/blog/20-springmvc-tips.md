@DatetimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")

@DateTimeFormat决定入参的格式
@JsonFormat决定出参的格式。


jackjson	@JsonFormat(pattern=“yyyy-MM-dd”)	-
fastjson	@JSONField(format = “yyyy-MM-dd”)	-
gson	    自定义TypeAdapter，然后加到字段上	无直接可用的注解