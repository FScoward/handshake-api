package controllers

import javax.inject.Inject

import com.twitter.finatra.http.Controller
import models.{Groups, GroupRequest}
import repository.GroupRepository
import utils.Id64

/**
  * Created by Fumiyasu on 2016/06/22.
  */
class GroupController @Inject()(groupRepository: GroupRepository) extends Controller {

  post("/group") { request: GroupRequest =>
    info("group")
    val id = Id64.nextAscId()

    groupRepository.save(Groups(id, request.name))
  }

}
