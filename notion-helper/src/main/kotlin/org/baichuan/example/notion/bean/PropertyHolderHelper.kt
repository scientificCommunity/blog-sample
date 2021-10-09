package org.baichuan.example.notion.bean

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/10/9
 */
object PropertyHolderHelper {

    @JvmStatic
    fun map(holderName: String, holder: Holder): Map<String, Holder> {
        val map: MutableMap<String, Holder> = HashMap()
        map[holderName] = holder
        return map
    }

    fun defineTradeAccount(): DefineSelectHolder {
        val tradeAccountSelectHolder = DefineSelectHolder()
        val tradeAccountDefineSelect = DefineSelect()
        //定义交易账号
        val yuebao = Option()
        yuebao.name = "余额宝"

        val huabei = Option()
        huabei.name = "花呗"

        val zhaoshan = Option()
        zhaoshan.name = "招商银行储蓄卡(6888)"

        val jianshe = Option()
        jianshe.name = "中国建设银行储蓄卡(5332)"

        tradeAccountDefineSelect.options = arrayOf(huabei, yuebao, zhaoshan, jianshe)
        tradeAccountSelectHolder.select = tradeAccountDefineSelect

        return tradeAccountSelectHolder
    }

    fun defineTradeCategory(): DefineRichTextHolder {
        val defineRichTextHolder = DefineRichTextHolder()
        val richText = RichText()
        defineRichTextHolder.richText = richText

        return defineRichTextHolder
    }

    fun defineTags(): DefineMultiSelectHolder {
        val defineSelectHolder = DefineMultiSelectHolder()
        val defineSelect = DefineSelect()
        val option = Option()
        option.name = "test1"
        defineSelect.options = arrayOf(option)
        defineSelectHolder.multiSelect = defineSelect

        return defineSelectHolder
    }

    fun createTags(vararg tags: String): CreateMultiSelectHolder {
        val array: MutableList<Select> = ArrayList(tags.size)
        for (tag in tags) {
            val multiSelect = Select()
            multiSelect.name = tag
            array.add(multiSelect)
        }

        val createMultiSelectHolder = CreateMultiSelectHolder()
        createMultiSelectHolder.multiSelect = array.toTypedArray()
        return createMultiSelectHolder
    }

    fun defineAmount(): DefineNumberPropertyHolder {
        val defineNumberPropertyHolder = DefineNumberPropertyHolder()
        val defineNumberProperty = DefineNumberProperty()
        defineNumberPropertyHolder.number = defineNumberProperty
        return defineNumberPropertyHolder
    }

    fun createAmount(amount: Double): CreateNumberHolder {
        val createNumberHolder = CreateNumberHolder()
        createNumberHolder.number = amount
        return createNumberHolder
    }

    fun definePropertyTitle(): TitleHolder {
        val titleHolder = TitleHolder()
        val title = Title()
        titleHolder.title = title
        return titleHolder
    }

    fun createPropertyTitle(titleStr: String): CreatePropertyTitleHolder {
        val title = Title()
        val text = Text()
        text.content = titleStr
        title.text = text

        val createPropertyTitleHolder = CreatePropertyTitleHolder()
        createPropertyTitleHolder.title = title

        return createPropertyTitleHolder
    }
}