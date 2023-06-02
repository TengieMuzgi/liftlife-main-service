package com.liftlife.liftlife.user.user;

import com.google.cloud.storage.Bucket;
import com.google.firebase.auth.FirebaseAuthException;
import com.liftlife.liftlife.common.CoachSpecialization;
import com.liftlife.liftlife.dto.CoachDto;
import com.liftlife.liftlife.security.AuthService;
import com.liftlife.liftlife.user.coach.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private Bucket firebaseBucket;

    @GetMapping("/token/generate")
    public ResponseEntity<String> generateRegistrationToken() {
        return userService.generateRegistrationToken();
    }

    @PostMapping("/token/verify")
    public ResponseEntity<String> registerWithToken(@RequestParam String token) throws FirebaseAuthException {
        return userService.verifyWithToken(token);
    }

    @PostMapping
    public void saveProfilePictureToBucket(@RequestParam("image") MultipartFile file) throws IOException {
        byte[] fileInBytes = file.getBytes();
        firebaseBucket.create(AuthService.getCurrentUserAuthId(), (InputStream) file);
    }

    @GetMapping("/coaches")
    public ResponseEntity<List<CoachDto>> getCoachesBySpecialization() throws FirebaseAuthException {
        return userService.getCoachList();
    }

    @PostMapping("/coach/change/description")
    public ResponseEntity<String> changeCoachDescription(@RequestBody String description) {
        return userService.changeCoachDescription(description);
    }

    @PostMapping("/coach/change/specialization")
    public ResponseEntity<String> changeCoachSpecialization(@RequestBody CoachSpecialization specialization) {
        return userService.changeCoachSpecialization(specialization);
    }


//    @GetMapping
//    public void getProfilePicture() throws FirebaseAuthException {
////        Blob downloadedBlob = firebaseBucket.get("MuA1nRxWA2OOpS0pNOSpZxMcKan2");
////        String url = downloadedBlob.getMediaLink();
//        UserRecord userRecord = AuthService.getCurrentUser();
//        System.out.println(userRecord.getPhotoUrl());
////        UserRecord.UpdateRequest request = new UserRecord.UpdateRequest(userRecord.getUid()).setPhotoUrl(url);
////        UserRecord updatedUser = firebaseAuth.updateUser(request);
////        System.out.println(url);
////        System.out.println(updatedUser.getPhotoUrl());
//    }


}
