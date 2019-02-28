package com.ukefu.webim.web.handler.api.rest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ukefu.util.Menu;
import com.ukefu.webim.service.es.QuickReplyRepository;
import com.ukefu.webim.util.RestResult;
import com.ukefu.webim.util.RestResultType;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.handler.api.request.SearchData;
import com.ukefu.webim.web.model.QuickReply;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/quickreply")
@Api(value = "快捷回复服务", description = "快捷回复管理功能")
public class ApiQuickReplyController extends Handler {

    @Autowired
    private QuickReplyRepository quickReplyRepository;

    /**
     * 返回快捷回复列表，支持分页，分页参数为 p=1&ps=50，默认分页尺寸为 20条每页
     * @param request
     * @param cate	搜索分类id，精确搜索，通过/api/quicktype 获取分类id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @Menu(type = "apps", subtype = "quickreply", access = true)
    @ApiOperation("返回快捷回复列表，cate为分类id，通过/api/quicktype 获取分类id，支持分页，分页参数为 p=1&ps=50，默认分页尺寸为 20条每页")
    public ResponseEntity<RestResult> list(HttpServletRequest request, String id, @Valid String cate, @Valid String q, Integer p, Integer ps) {
        if (StringUtils.isNotBlank(id)) {
            return new ResponseEntity<>(new RestResult(RestResultType.OK, quickReplyRepository.findOne(id)), HttpStatus.OK);
        }

        Page<QuickReply> replyList = quickReplyRepository.getByOrgiAndCate(getOrgi(request), cate, q,
                new PageRequest(p == null ? 1 : p, ps == null ? 20 : ps));
        return new ResponseEntity<>(new RestResult(RestResultType.OK, new SearchData<QuickReply>(replyList)), HttpStatus.OK);
    }

    /**
     * 新增或修改快捷回复
     * @param request
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    @Menu(type = "apps", subtype = "quickreply", access = true)
    @ApiOperation("新增或修改快捷回复")
    public ResponseEntity<RestResult> put(HttpServletRequest request, @Valid QuickReply quickReply) {
        if (quickReply != null && !StringUtils.isBlank(quickReply.getTitle())) {
            quickReply.setOrgi(getOrgi(request));
            quickReply.setCreater(getUser(request).getId());
            quickReplyRepository.save(quickReply);
        }
        return new ResponseEntity<>(new RestResult(RestResultType.OK), HttpStatus.OK);
    }

    /**
     * 删除用户，只提供 按照用户ID删除
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @Menu(type = "apps", subtype = "quickreply", access = true)
    @ApiOperation("根据id删除快捷回复")
    public ResponseEntity<RestResult> delete(HttpServletRequest request, @Valid String id) {
        RestResult result = new RestResult(RestResultType.OK);
        if (!StringUtils.isBlank(id)) {
            QuickReply reply = quickReplyRepository.findOne(id);
            if (reply != null) {
                quickReplyRepository.delete(reply);
            } else {
                return new ResponseEntity<>(new RestResult(RestResultType.ORGAN_DELETE), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
