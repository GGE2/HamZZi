package com.ssafy.api.controller;

import com.ssafy.api.request.ItemRequest;
import com.ssafy.api.request.ItemUserRequest;
import com.ssafy.api.service.ItemService;
import com.ssafy.db.entity.Item.Item;
import com.ssafy.db.entity.Item.ItemUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Item API", tags = {"Item"})
@RestController
@RequestMapping("api/item")
@RequiredArgsConstructor
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/itemAllList")
    @ApiOperation(value = "Item 전체 리스트 조회", notes = "Item 전체 리스트를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    private ResponseEntity<?> getItemAllList() {
        List<Item> items = itemService.itemAllList();
        return ResponseEntity.status(200).body(items);
    }

    @GetMapping("/itemUserAllList")
    @ApiOperation(value = "ItemUser 리스트 전체 조회", notes = "ItemUser 리스트 전체 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> getItemUserAllList() {
        List<ItemUser> itemUsers = itemService.itemUserAllList();
        return ResponseEntity.status(200).body(itemUsers);
    }

    @GetMapping("/itemUserList/{nickname}")
    @ApiOperation(value = "Nickname로 ItemUser 리스트 조회", notes = "Nickname로 ItemUser 리스트 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    private ResponseEntity<?> getItemUserList(@PathVariable String nickname) {
        List<ItemUser> itemUsers = itemService.itemUserList(nickname);
        return ResponseEntity.status(200).body(itemUsers);
    }

    @GetMapping("/wear/{nickname}")
    @ApiOperation(value = "Nickname로 ItemUser wear 리스트 조회", notes = "Nickname로 ItemUser wear 리스트 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> getWearList(@PathVariable String nickname) {
        List<ItemUser> itemUserList = itemService.findByTrue(nickname);
        return ResponseEntity.status(200).body(itemUserList);
    }

    @PostMapping("/createItem/{type}")
    @ApiOperation(value = "Item 생성", notes = "Item 생성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String createItem(@RequestBody ItemRequest itemReq, @PathVariable int type) {
        Item item = itemService.createItem(itemReq, type);

        return "Item ID: " + item.getItem_id() + " Item COST: " + item.getCost() + " Item LEVEL: " + item.getLevel() + " Item TYPE: " + item.getType();
    }

    @PostMapping("/createItemUser/{item_id}")
    @ApiOperation(value = "ItemUser 생성", notes = "ItemUser 생성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String createItemUser(@RequestBody ItemUserRequest itemUserReq, @PathVariable int item_id) {
        ItemUser itemUser = itemService.createItemUser(itemUserReq, item_id);

        return "ID: " + itemUser.getItemUser_id();
    }

    @PutMapping("/{itemUser_id}")
    @ApiOperation(value = "ItemUser Wear 수정", notes = "ItemUser Wear 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String updateItemUserWear(@PathVariable Long itemUser_id) {
        ItemUser itemUser = itemService.updateWear(itemUser_id);
        return "ID: " + itemUser.getItemUser_id() + " Wear: " + itemUser.getWear();
    }

}