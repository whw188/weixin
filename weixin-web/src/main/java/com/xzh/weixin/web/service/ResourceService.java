package com.xzh.weixin.web.service;

import com.xzh.weixin.web.common.ResponseDTO;
import com.xzh.weixin.web.common.ReturnCode;
import com.xzh.weixin.web.dao.ResourceMapper;
import com.xzh.weixin.web.dao.model.RecomondModel;
import com.xzh.weixin.web.dao.model.ResourceModel;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuzh on 2018/2/27 20:14.
 */
@Service
public class ResourceService {

    @Resource
    ResourceMapper resourceMapper;

    @Resource
    RecommondService recommondService;


    public ResponseDTO<List<ResourceModel>> recommond(String uid) {

        ResponseDTO<List<ResourceModel>> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {


            List<ResourceModel> result = new ArrayList<>();

            ResponseDTO<List<RecomondModel>> listResponseDTO = recommondService.selectByUid(uid);

            if (listResponseDTO.getCode() == ReturnCode.ACTIVE_SUCCESS.code()) {

                List<RecomondModel> attach = listResponseDTO.getAttach();

                if (attach != null && attach.size() > 0) {

                    RecomondModel recomondModel = attach.get(0);

                    String search = recomondModel.getSearch();

                    ResponseDTO<List<ResourceModel>> searchResult = selectByTitleOrAuthor(search, null);

                    if (searchResult.getCode() == ReturnCode.ACTIVE_SUCCESS.code()) {
                        List<ResourceModel> attach1 = searchResult.getAttach();

                        if (attach1 != null) {
                            result.addAll(attach1);
                        }
                    }
                }
            }

            List<ResourceModel> recommond = resourceMapper.recommond();

            if (recommond != null) {
                List<ResourceModel> tmp = new ArrayList<>();

                if (result.size() > 0) {

                    for (ResourceModel resourceModel : result) {
                        for (ResourceModel model : recommond) {
                            if (resourceModel.getRid().longValue() == model.getRid().longValue()) {
                                tmp.add(model);
                            }
                        }
                    }
                }
                result.addAll(recommond);
                result.removeAll(tmp);
            }

            if (result.size() > 4) {
                result = result.subList(0, 4);
            }

            responseDTO.setAttach(result);
            responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }


    public ResponseDTO<String> insert(ResourceModel resourceModel) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            int insert = resourceMapper.insert(resourceModel);
            if (insert > 0) {
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }

    public ResponseDTO<String> updateAgree(long rid) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        try {
            int updateAgree = resourceMapper.updateAgree(rid);
            if (updateAgree > 0) {
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }

    public ResponseDTO<String> updateView(long rid) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        try {
            int updateView = resourceMapper.updateView(rid);
            if (updateView > 0) {
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }

    public ResponseDTO<String> updateShelf(long rid) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        try {
            int updateShelf = resourceMapper.updateShelf(rid);
            if (updateShelf > 0) {
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }

    public ResponseDTO<String> updateStatus(long rid, int status) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        try {
            int updateShelf = resourceMapper.updateStatus(status, rid);
            if (updateShelf > 0) {
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }

    public ResponseDTO<ResourceModel> selectById(long rid) {

        ResponseDTO<ResourceModel> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            ResourceModel resourceModel = resourceMapper.selectById(rid);
            if (resourceModel != null) {
                responseDTO.setAttach(resourceModel);
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }

    public ResponseDTO<ResourceModel> downloadRid(long rid) {

        ResponseDTO<ResourceModel> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            ResourceModel resourceModel = resourceMapper.downloadRid(rid);
            if (resourceModel != null) {
                responseDTO.setAttach(resourceModel);
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }

    public ResponseDTO<List<ResourceModel>> selectByStatus(int status) {

        ResponseDTO<List<ResourceModel>> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            List<ResourceModel> resourceModel = resourceMapper.selectByStatus(status);
            if (resourceModel == null) {
                resourceModel = new ArrayList<>();
            }
            responseDTO.setAttach(resourceModel);
            responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }

    public ResponseDTO<List<ResourceModel>> selectByCid(long cid) {

        ResponseDTO<List<ResourceModel>> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            List<ResourceModel> resourceModels = resourceMapper.selectByCid(cid);
            responseDTO.setAttach(resourceModels);
            responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }


    public ResponseDTO<List<ResourceModel>> selectByCidAndType(long cid, String type) {

        ResponseDTO<List<ResourceModel>> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            List<ResourceModel> resourceModels = resourceMapper.selectByCidAndType(cid, type);
            responseDTO.setAttach(resourceModels);
            responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }

    public ResponseDTO<List<ResourceModel>> selectByTitleOrAuthor(String keyWord, String uid) {

        ResponseDTO<List<ResourceModel>> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            List<ResourceModel> resourceModels = resourceMapper.selectByTitleOrAuthor(keyWord, keyWord);

            addSearch(resourceModels, uid, keyWord);

            responseDTO.setAttach(resourceModels);
            responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }

    public void addSearch(List<ResourceModel> resourceModels, String uid, String keyWord) {
        if (uid != null) {
            if (resourceModels != null && resourceModels.size() > 0) {
                ResponseDTO<List<RecomondModel>> listResponseDTO = recommondService.selectByUid(uid);
                if (listResponseDTO.getCode() == ReturnCode.ACTIVE_SUCCESS.code()) {

                    List<RecomondModel> attach = listResponseDTO.getAttach();
                    if (attach != null && attach.size() > 0) {
                        recommondService.update(uid, keyWord);
                    } else {
                        RecomondModel recomondModel = new RecomondModel();
                        recomondModel.setUid(uid);
                        recomondModel.setSearch(keyWord);
                        recommondService.insert(recomondModel);
                    }
                }
            }
        }
    }

    public ResponseDTO<List<ResourceModel>> selectByTitleAndCid(String keyWord, String uid, long cid) {

        ResponseDTO<List<ResourceModel>> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            List<ResourceModel> resourceModels = resourceMapper.selectByTitleAndCid(keyWord, keyWord, cid);
            addSearch(resourceModels, uid, keyWord);
            responseDTO.setAttach(resourceModels);
            responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }

    public ResponseDTO<List<ResourceModel>> selectByUid(String uid) {

        ResponseDTO<List<ResourceModel>> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            List<ResourceModel> resourceModels = resourceMapper.selectByUid(uid);
            responseDTO.setAttach(resourceModels);
            responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }
}
