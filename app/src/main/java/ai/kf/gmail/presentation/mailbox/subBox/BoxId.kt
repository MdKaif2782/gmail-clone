package ai.kf.gmail.presentation.mailbox.subBox

sealed class BoxId {
    public val PRIMARY:Int = 0
    public val SOCIAL:Int = 1
    public val PROMOTIONAL:Int = 2
    public val DRAFT:Int = 3
    public val TRASH:Int = 4
}