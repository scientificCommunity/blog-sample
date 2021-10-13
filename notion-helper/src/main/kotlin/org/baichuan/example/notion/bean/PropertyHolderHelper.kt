package org.baichuan.example.notion.bean

import org.baichuan.example.notion.enum.TradeType
import org.baichuan.example.notion.utils.ColorHelper

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

    fun <T : Enum<T>> defineSelect(options: Array<T>): DefineSelectHolder {
        return defineSelect(options, null)
    }

    fun <T : Enum<T>> defineSelect(options: Array<T>, exclude: T?): DefineSelectHolder {
        val tradeAccountSelectHolder = DefineSelectHolder()
        val tradeAccountDefineSelect = DefineSelect()
        val list: MutableList<Option> = ArrayList()
        options.forEach {
            if (exclude == null || it != exclude) {
                //定义交易账号
                val option = Option()
                val field = it.javaClass.getDeclaredField("value")
                field.isAccessible = true
                val value = field.get(it)
                option.name = value.toString()
                option.color = ColorHelper.setColor(value.toString())
                list.add(option)
            }
        }

        tradeAccountDefineSelect.options = list.toTypedArray()
        tradeAccountSelectHolder.select = tradeAccountDefineSelect

        return tradeAccountSelectHolder
    }

    fun createSelect(value: String): CreateSelectHolder {
        val createSelectHolder = CreateSelectHolder()
        val select = Select()
        select.name = value
        createSelectHolder.select = select
        return createSelectHolder
    }

    fun defineRichText(): DefineRichTextHolder {
        val defineRichTextHolder = DefineRichTextHolder()
        val richText = RichText()
        defineRichTextHolder.richText = richText

        return defineRichTextHolder
    }

    fun createRichText(content: String): CreateRichTextHolder {
        val defineRichTextHolder = CreateRichTextHolder()
        val richText = RichText()
        val text = Text()
        text.content = content
        richText.text = text
        defineRichTextHolder.richText = arrayOf(richText)

        return defineRichTextHolder
    }

    fun defineMultiSelect(): DefineMultiSelectHolder {
        val defineSelectHolder = DefineMultiSelectHolder()
        val defineSelect = DefineSelect()
        defineSelect.options = arrayOf()
        defineSelectHolder.multiSelect = defineSelect

        return defineSelectHolder
    }

    fun createMultiSelect(vararg tags: String): CreateMultiSelectHolder {
        val array: MutableList<Select> = ArrayList(tags.size)
        for (tag in tags) {
            val multiSelect = Select()
            multiSelect.name = tag
            //multiSelect.color = ColorHelper.setColor(tag)
            array.add(multiSelect)
        }

        val createMultiSelectHolder = CreateMultiSelectHolder()
        createMultiSelectHolder.multiSelect = array.toTypedArray()
        return createMultiSelectHolder
    }

    fun defineNumber(): DefineNumberPropertyHolder {
        val defineNumberPropertyHolder = DefineNumberPropertyHolder()
        val defineNumberProperty = DefineNumberProperty()
        defineNumberPropertyHolder.number = defineNumberProperty
        return defineNumberPropertyHolder
    }

    fun createNumber(amount: Double): CreateNumberHolder {
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
        createPropertyTitleHolder.title = arrayOf(title)

        return createPropertyTitleHolder
    }

    fun defineDate(): DefineDatePropertyHolder {
        return DefineDatePropertyHolder()
    }

    fun createDate(date: String): CreateDatePropertyHolder {
        val createDatePropertyHolder = CreateDatePropertyHolder()
        val createDateProperty = CreateDateProperty()
        createDateProperty.start = date
        createDatePropertyHolder.date = createDateProperty
        return createDatePropertyHolder
    }
}

fun main() {
    PropertyHolderHelper.defineSelect(TradeType.values())
}