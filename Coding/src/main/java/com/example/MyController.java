
        @RestController
        public class MyController {
            @GetMapping("/api/secure")
            public ResponseEntity<Void> secureEndpoint() {
                return ResponseEntity.ok().build();
            }
        }
    