package com.sparta.springboot.service;


import com.sparta.springboot.entity.Folder;
import com.sparta.springboot.entity.Product;
import com.sparta.springboot.entity.User;
import com.sparta.springboot.repository.FolderRepository;
import com.sparta.springboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FolderService {

    private final FolderRepository folderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public FolderService(FolderRepository folderRepository, ProductRepository productRepository) {
        this.folderRepository = folderRepository;
        this.productRepository = productRepository;
    }

    // 로그인한 회원에 폴더들 등록
    public List<Folder> addFolders(List<String> folderNames, User user) {
        List<Folder> folderList = new ArrayList<>();

        for (String folderName : folderNames) {
            Folder folder = new Folder(folderName, user);
            folderList.add(folder);
        }

        return folderRepository.saveAll(folderList);
    }

    // 로그인한 회원이 등록된 모든 폴더 조회
    public List<Folder> getFolders(User user) {
        return folderRepository.findAllByUser(user);
    }


    // 회원 ID 가 소유한 폴더에 저장되어 있는 상품들 조회
    public Page<Product> getProductsInFolder(
            Long folderId,
            int page,
            int size,
            String sortBy,
            boolean isAsc,
            User user
    ) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Long userId = user.getId();
        return productRepository.findAllByUserIdAndFolderList_Id(userId, folderId, pageable);
    }
}
