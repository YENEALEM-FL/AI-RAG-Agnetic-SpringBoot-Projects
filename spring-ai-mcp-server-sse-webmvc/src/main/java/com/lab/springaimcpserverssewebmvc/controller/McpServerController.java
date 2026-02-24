package com.lab.springaimcpserverssewebmvc.controller;

import com.lab.springaimcpserverssewebmvc.service.HotelBookingService;
import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.server.McpSyncServer;
import lombok.AllArgsConstructor;
import org.springframework.ai.mcp.McpToolUtils;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class McpServerController {

    McpSyncServer mcpSyncServer;

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(McpServerController.class);

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
