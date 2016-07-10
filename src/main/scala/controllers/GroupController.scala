package controllers

import javax.inject.Inject

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import models.{ GroupRequest, Groups, GroupsHasUsers }
import repository.{ GroupRepository, GroupsHasUsersRepository }
import utils.Id64

/**
 * Created by Fumiyasu on 2016/06/22.
 */
class GroupController @Inject() (groupRepository: GroupRepository, groupsHasUsersRepository: GroupsHasUsersRepository) extends Controller {

  post("/group") { request: GroupRequest =>
    info("group")
    val id = Id64.nextAscId()

    groupRepository.save(Groups(id, request.name))
  }

  get("/group") { request: Request =>
    val list = groupRepository.list()
    list
  }

  post("/group/join") { request: GroupsHasUsers =>
    groupsHasUsersRepository.join(request)
  }

}
