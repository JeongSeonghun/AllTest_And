package com.jsh.kr.alltestkt.constant

import androidx.annotation.StringDef

object C {

    object BroadCast {

        const val CATEGORY_LOCAL = "com.jsh.kr.alltestkt.LOCAL"
        const val Data = "data"

        const val CategoryEdit = "CategoryEdit"

        @Retention(AnnotationRetention.SOURCE)
        @StringDef(
                CategoryEdit)
        annotation class Action

    }

    const val PACKAGE = "com.jsh.kr.alltestkt.tizentestapp"

    object RequestCode {
        const val PermissionRequest = 1
        const val Camera = 2
        const val Album = 3
        const val CropImage = 4
        const val PermissionRequestImageSelect = 5
        const val PermissionRequestFileTest = 6

        const val MoveTest = 7
        const val EnableBluetooth = 8
    }

    /**
     * 2 : add file display table
     * 3 : add asset db test
     */
    object Database {
        const val Name = "alltestDB"
        const val Version = 3

        object TbDBTest {
            const val Name = "dbTest"
            const val Idx = "idx"
            const val ColText = "col_txt"
            const val ColInt = "col_int"
        }

        object TbFileDisplay {
            const val Name = "fileDisplay"
            const val Idx = "idx"
            const val ColCategory = "category"
            const val ColSubCategory1 = "subCategory1"
            const val ColSubCategory2 = "subCategory2"
            const val ColFileName = "fileName"
            const val ColPath = "path"
            const val ColData = "data"
        }

        object TbFileDisplayCategory {
            const val Name = "fileDisplayCategory"
            const val ColIdx = "idx"
            const val ColParentId = "parentId"
            const val ColCategory = "category"
            const val ColName = "name"
            const val ColLevel = "level"
            const val ColSubCategory1 = "subCategory1"
            const val ColSubCategory2 = "subCategory2"
        }

        object TbFileHistory {
            const val Name = "fileHistory"
            const val ColIdx = "idx"
            const val ColName = "name"
            const val ColDate = "date"
        }

        object TbAssetDBTest {
            const val Name = "AssetDBTest"
            const val ColIdx = "idx"
            const val ColName = "name"

        }
    }

    object TAG {
        const val All = "all"
        const val Etc = "etc"
        const val Issue = "issue"
    }

    object State {
        const val No_State = 0
        const val Proceed = 1
        const val Complete = 2
        const val Resolution = 3
        const val Test = 4
        const val Close = 5
    }

    object StateColor {
        const val No_State = "#000000"
        const val Proceed = "#ff0000"
        const val Complete = "#00ff00"
        const val Resolution = "#0000ff"
        const val Test = "#ff0000"
        const val Close = "#0000ff"
    }

    object Maintain {
        const val Menu_UI = 1
        const val Menu_Action = 2
    }

}
