
>	auto generate image to markup

<#list imgList as image>
## ${image}

![${image}](./img/${image} "${image}") 

</#list>