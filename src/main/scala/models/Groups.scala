package models

/**
  * Created by Fumiyasu on 2016/06/22.
  */
case class GroupRequest(name: String)
case class Groups(groupId: Long, groupname: String)

case class Group(groupId: Long, name: String, member: List[User])
