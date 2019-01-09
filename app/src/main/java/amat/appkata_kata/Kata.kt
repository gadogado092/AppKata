package amat.appkata_kata

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Kata(val id:Int?,val judul:String?,val isi:String?) : Parcelable {

    companion object {
        const val TABLE_KATA = "TABLE_KATA"
        const val ID_KATA = "ID_KATA"
        const val JUDUL_KATA = "JUDUL_KATA"
        const val ISI_KATA = "ISI_KATA"

    }
}