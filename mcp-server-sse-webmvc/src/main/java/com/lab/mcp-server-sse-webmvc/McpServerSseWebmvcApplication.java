package com.lab.mcp-server-sse-webmvc;

import com.example.mcp_server_sse_webmvc.service.HotelBookingService;
import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.server.McpSyncServer;
import lombok.AllArgsConstructor;
import org.springframework.ai.mcp.McpToolUtils;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@AllArgsConstructor
public class McpServerSseWebmvcApplication {

    McpSyncServer mcpSyncServer ;

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(McpServerSseWebmvcApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(McpServerSseWebmvcApplication.class, args);
    }

    @GetMapping("/updateTools")
    public String greeting() {
        System.out.println("Update tools signal received!");
        List<McpServerFeatures.SyncToolSpecification> newTools = McpToolUtils
                .toSyncToolSpecifications(ToolCallbacks.from(new HotelBookingService()));

        for (McpServerFeatures.SyncToolSpecification newTool : newTools) {
            logger.info("Add new tool: " + newTool);
            mcpSyncServer.addTool(newTool);
        }
        return "Update signal received!";
    }
}
