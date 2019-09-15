package com.verdantartifice.primalmagic.client.gui.grimoire;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.GlStateManager;
import com.verdantartifice.primalmagic.PrimalMagic;
import com.verdantartifice.primalmagic.client.gui.grimoire.pages.AbstractPage;
import com.verdantartifice.primalmagic.client.gui.grimoire.pages.DisciplinePage;
import com.verdantartifice.primalmagic.client.gui.grimoire.pages.IndexPage;
import com.verdantartifice.primalmagic.client.gui.grimoire.pages.PageImage;
import com.verdantartifice.primalmagic.client.gui.grimoire.pages.PageString;
import com.verdantartifice.primalmagic.client.gui.grimoire.pages.RequirementsPage;
import com.verdantartifice.primalmagic.client.gui.grimoire.pages.StagePage;
import com.verdantartifice.primalmagic.client.gui.grimoire.widgets.BackButton;
import com.verdantartifice.primalmagic.client.gui.grimoire.widgets.DisciplineButton;
import com.verdantartifice.primalmagic.client.gui.grimoire.widgets.EntryButton;
import com.verdantartifice.primalmagic.client.gui.grimoire.widgets.PageButton;
import com.verdantartifice.primalmagic.client.gui.grimoire.widgets.SectionHeaderWidget;
import com.verdantartifice.primalmagic.common.capabilities.IPlayerKnowledge;
import com.verdantartifice.primalmagic.common.capabilities.PrimalMagicCapabilities;
import com.verdantartifice.primalmagic.common.containers.GrimoireContainer;
import com.verdantartifice.primalmagic.common.research.ResearchAddendum;
import com.verdantartifice.primalmagic.common.research.ResearchDiscipline;
import com.verdantartifice.primalmagic.common.research.ResearchDisciplines;
import com.verdantartifice.primalmagic.common.research.ResearchEntry;
import com.verdantartifice.primalmagic.common.research.ResearchStage;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GrimoireScreen extends ContainerScreen<GrimoireContainer> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(PrimalMagic.MODID, "textures/gui/grimoire.png");
    private static final PageImage IMAGE_LINE = PageImage.parse("primalmagic:textures/gui/grimoire.png:24:184:95:6:1");
    private static final float SCALE = 1.3F;
    
    public static final List<Object> HISTORY = new ArrayList<>();
    
    protected int scaledLeft;
    protected int scaledTop;
    protected int currentPage = 0;
    protected int currentStageIndex = 0;
    protected int lastStageIndex = 0;
    protected long lastCheck = 0L;
    protected boolean progressing = false;
    protected List<AbstractPage> pages = new ArrayList<>();
    protected IPlayerKnowledge knowledge;
    
    protected PageButton nextPageButton;
    protected PageButton prevPageButton;
    protected BackButton backButton;
    
    public GrimoireScreen(GrimoireContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.xSize = 256;
        this.ySize = 181;
    }
    
    public PlayerInventory getPlayerInventory() {
        return this.playerInventory;
    }
    
    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        long millis = System.currentTimeMillis();
        if (millis > this.lastCheck) {
            this.lastCheck = this.progressing ? (millis + 250L) : (millis + 2000L);
            this.initPages();
            this.initButtons();
            if (this.currentStageIndex > this.lastStageIndex) {
                this.progressing = false;
                this.lastStageIndex = this.currentStageIndex;
                this.currentPage = 0;
                this.updateNavButtonVisibility();
            }
        }
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
    
    public boolean isProgressing() {
        return this.progressing;
    }
    
    public void setProgressing() {
        this.progressing = true;
        this.lastCheck = 0L;
        this.lastStageIndex = this.currentStageIndex;
    }
    
    @Override
    protected void init() {
        super.init();
        this.scaledLeft = (int)(this.width - this.xSize * SCALE) / 2;
        this.scaledTop = (int)(this.height - this.ySize * SCALE) / 2;
        this.knowledge = PrimalMagicCapabilities.getKnowledge(this.getMinecraft().player);
        if (this.knowledge == null) {
            throw new IllegalStateException("No knowledge provider found for player");
        }
        this.initPages();
        this.initButtons();
    }
    
    protected void initPages() {
        this.pages.clear();
        if (this.container.getTopic() == null) {
            this.parseIndexPages();
        } else if (this.container.getTopic() instanceof ResearchDiscipline) {
            this.parseDisciplinePages((ResearchDiscipline)this.container.getTopic());
        } else if (this.container.getTopic() instanceof ResearchEntry) {
            this.parseEntryPages((ResearchEntry)this.container.getTopic());
        }
    }
    
    protected void initButtons() {
        int current = 0;
        this.buttons.clear();
        this.children.clear();
        for (AbstractPage page : this.pages) {
            if ((current == this.currentPage || current == this.currentPage + 1) && current < this.pages.size()) {
                this.initPageButtons(page, current % 2, this.scaledLeft + 23, this.scaledTop + 9);
            }
            current++;
            if (current > this.currentPage + 1) {
                break;
            }
        }
        this.nextPageButton = new PageButton(this.guiLeft + 262, this.guiTop + 190, this, true);
        this.prevPageButton = new PageButton(this.guiLeft - 16, this.guiTop + 190, this, false);
        this.backButton = new BackButton(this.guiLeft + 118, this.guiTop + 190, this);
        this.addButton(this.nextPageButton);
        this.addButton(this.prevPageButton);
        this.addButton(this.backButton);
        this.updateNavButtonVisibility();
    }
    
    public <T extends Widget> T addWidgetToScreen(T widget) {
        return this.addButton(widget);
    }

    private void initPageButtons(AbstractPage abstractPage, int side, int x, int y) {
        // Make room for page title if applicable
        if (this.currentPage == 0 && side == 0) {
            y += 28;
        }
        if (this.currentPage > 0 || side == 1) {
            y += 4;
        }
        
        // Place buttons
        if (abstractPage instanceof IndexPage) {
            IndexPage page = (IndexPage)abstractPage;
            for (ResearchDiscipline discipline : page.getDisciplines()) {
                String text = (new TranslationTextComponent(discipline.getNameTranslationKey())).getFormattedText();
                this.addButton(new DisciplineButton(x + (side * 152), y, text, this, discipline));
                y += 12;
            }
        } else if (abstractPage instanceof DisciplinePage) {
            DisciplinePage page = (DisciplinePage)abstractPage;
            for (Object obj : page.getContents()) {
                if (obj instanceof ResearchEntry) {
                    ResearchEntry entry = (ResearchEntry)obj;
                    String text = (new TranslationTextComponent(entry.getNameTranslationKey())).getFormattedText();
                    this.addButton(new EntryButton(x + (side * 152), y, text, this, entry));
                } else if (obj instanceof ITextComponent) {
                    String text = ((ITextComponent)obj).getFormattedText();
                    this.addButton(new SectionHeaderWidget(x + (side * 152), y, text));
                }
                y += 12;
            }
        } else if (abstractPage instanceof RequirementsPage) {
            ((RequirementsPage)abstractPage).initWidgets(this, side, x, y);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(TEXTURE);

        int unscaledLeft = (this.width - this.xSize) / 2;
        int unscaledTop = (this.height - this.ySize) / 2;
        float scaledLeft = (this.width - this.xSize * SCALE) / 2.0F;
        float scaledTop = (this.height - this.ySize * SCALE) / 2.0F;

        GlStateManager.pushMatrix();
        GlStateManager.translatef(scaledLeft, scaledTop, 0.0F);
        GlStateManager.scalef(SCALE, SCALE, 1.0F);
        this.blit(0, 0, 0, 0, this.xSize, this.ySize);
        GlStateManager.popMatrix();
        
        int current = 0;
        for (AbstractPage page : this.pages) {
            if ((current == this.currentPage || current == this.currentPage + 1) && current < this.pages.size()) {
                page.render(current % 2, unscaledLeft, unscaledTop - 10, mouseX, mouseY);
            }
            current++;
            if (current > this.currentPage + 1) {
                break;
            }
        }
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }
    
    private List<ResearchDiscipline> buildDisciplineList() {
        return ResearchDisciplines.getAllDisciplines().stream()
                                    .sorted(Comparator.comparing(d -> (new TranslationTextComponent(d.getNameTranslationKey())).getString()))
                                    .collect(Collectors.toList());
    }
    
    private List<ResearchEntry> buildEntryList(ResearchDiscipline discipline) {
        return discipline.getEntries().stream()
                .sorted(Comparator.comparing(e -> (new TranslationTextComponent(e.getNameTranslationKey())).getString()))
                .collect(Collectors.toList());
    }
    
    protected void parseIndexPages() {
        this.currentStageIndex = 0;
        List<ResearchDiscipline> disciplines = this.buildDisciplineList();
        if (disciplines.isEmpty()) {
            return;
        }
        
        int heightRemaining = 182;
        IndexPage tempPage = new IndexPage(true);
        for (ResearchDiscipline discipline : disciplines) {
            if (discipline.getUnlockResearchKey() == null || discipline.getUnlockResearchKey().isKnownByStrict(Minecraft.getInstance().player)) {
                tempPage.addDiscipline(discipline);
                heightRemaining -= 12;
                if (heightRemaining < 12 && !tempPage.getDisciplines().isEmpty()) {
                    heightRemaining = 210;
                    this.pages.add(tempPage);
                    tempPage = new IndexPage();
                }
            }
        }
        if (!tempPage.getDisciplines().isEmpty()) {
            this.pages.add(tempPage);
        }
    }
    
    protected void parseDisciplinePages(ResearchDiscipline discipline) {
        this.currentStageIndex = 0;
        if (discipline == null) {
            return;
        }
        List<ResearchEntry> entries = this.buildEntryList(discipline);
        if (entries.isEmpty()) {
            return;
        }
        
        // Sort the alphabetized entries into sections
        List<ResearchEntry> newList = new ArrayList<>();
        List<ResearchEntry> updatedList = new ArrayList<>();
        List<ResearchEntry> completeList = new ArrayList<>();
        List<ResearchEntry> inProgressList = new ArrayList<>();
        List<ResearchEntry> availableList = new ArrayList<>();
        for (ResearchEntry entry : entries) {
            if (this.knowledge.hasResearchFlag(entry.getKey(), IPlayerKnowledge.ResearchFlag.NEW)) {
                newList.add(entry);
            } else if (this.knowledge.hasResearchFlag(entry.getKey(), IPlayerKnowledge.ResearchFlag.UPDATED)) {
                updatedList.add(entry);
            } else if (this.knowledge.getResearchStatus(entry.getKey()) == IPlayerKnowledge.ResearchStatus.COMPLETE) {
                completeList.add(entry);
            } else if (this.knowledge.getResearchStatus(entry.getKey()) == IPlayerKnowledge.ResearchStatus.IN_PROGRESS) {
                inProgressList.add(entry);
            } else if (entry.getParentResearch() == null || entry.getParentResearch().isKnownByStrict(Minecraft.getInstance().player)) {
                availableList.add(entry);
            }
        }
        
        // Divide the sections out into pages
        int heightRemaining = 182;
        boolean firstSection = true;
        DisciplinePage tempPage = new DisciplinePage(discipline, true);
        if (!updatedList.isEmpty()) {
            // Append the section header and spacer
            ITextComponent headerText = new TranslationTextComponent("primalmagic.grimoire.section_header.updated").applyTextStyle(TextFormatting.UNDERLINE);
            if (heightRemaining < 36 && !tempPage.getContents().isEmpty()) {
                // If there's not room for the spacer, the header, and a first entry, skip to the next page
                heightRemaining = 210;
                this.pages.add(tempPage);
                tempPage = new DisciplinePage(discipline);
                tempPage.addContent(headerText);
            } else {
                if (!firstSection && !tempPage.getContents().isEmpty()) {
                    tempPage.addContent(new StringTextComponent(""));
                    heightRemaining -= 12;
                }
                tempPage.addContent(headerText);
                heightRemaining -= 12;
            }
            firstSection = false;
            for (ResearchEntry entry : updatedList) {
                // Append each entry from the list to the page, breaking where necessary
                tempPage.addContent(entry);
                heightRemaining -= 12;
                if (heightRemaining < 12 && !tempPage.getContents().isEmpty()) {
                    heightRemaining = 210;
                    this.pages.add(tempPage);
                    tempPage = new DisciplinePage(discipline);
                }
            }
        }
        if (!newList.isEmpty()) {
            // Append the section header and spacer
            ITextComponent headerText = new TranslationTextComponent("primalmagic.grimoire.section_header.new").applyTextStyle(TextFormatting.UNDERLINE);
            if (heightRemaining < 36 && !tempPage.getContents().isEmpty()) {
                // If there's not room for the spacer, the header, and a first entry, skip to the next page
                heightRemaining = 210;
                this.pages.add(tempPage);
                tempPage = new DisciplinePage(discipline);
                tempPage.addContent(headerText);
            } else {
                if (!firstSection && !tempPage.getContents().isEmpty()) {
                    tempPage.addContent(new StringTextComponent(""));
                    heightRemaining -= 12;
                }
                tempPage.addContent(headerText);
                heightRemaining -= 12;
            }
            firstSection = false;
            for (ResearchEntry entry : newList) {
                // Append each entry from the list to the page, breaking where necessary
                tempPage.addContent(entry);
                heightRemaining -= 12;
                if (heightRemaining < 12 && !tempPage.getContents().isEmpty()) {
                    heightRemaining = 210;
                    this.pages.add(tempPage);
                    tempPage = new DisciplinePage(discipline);
                }
            }
        }
        if (!inProgressList.isEmpty()) {
            // Append the section header and spacer
            ITextComponent headerText = new TranslationTextComponent("primalmagic.grimoire.section_header.in_progress").applyTextStyle(TextFormatting.UNDERLINE);
            if (heightRemaining < 36 && !tempPage.getContents().isEmpty()) {
                // If there's not room for the spacer, the header, and a first entry, skip to the next page
                heightRemaining = 210;
                this.pages.add(tempPage);
                tempPage = new DisciplinePage(discipline);
                tempPage.addContent(headerText);
            } else {
                if (!firstSection && !tempPage.getContents().isEmpty()) {
                    tempPage.addContent(new StringTextComponent(""));
                    heightRemaining -= 12;
                }
                tempPage.addContent(headerText);
                heightRemaining -= 12;
            }
            firstSection = false;
            for (ResearchEntry entry : inProgressList) {
                // Append each entry from the list to the page, breaking where necessary
                tempPage.addContent(entry);
                heightRemaining -= 12;
                if (heightRemaining < 12 && !tempPage.getContents().isEmpty()) {
                    heightRemaining = 210;
                    this.pages.add(tempPage);
                    tempPage = new DisciplinePage(discipline);
                }
            }
        }
        if (!availableList.isEmpty()) {
            // Append the section header and spacer
            ITextComponent headerText = new TranslationTextComponent("primalmagic.grimoire.section_header.available").applyTextStyle(TextFormatting.UNDERLINE);
            if (heightRemaining < 36 && !tempPage.getContents().isEmpty()) {
                // If there's not room for the spacer, the header, and a first entry, skip to the next page
                heightRemaining = 210;
                this.pages.add(tempPage);
                tempPage = new DisciplinePage(discipline);
                tempPage.addContent(headerText);
            } else {
                if (!firstSection && !tempPage.getContents().isEmpty()) {
                    tempPage.addContent(new StringTextComponent(""));
                    heightRemaining -= 12;
                }
                tempPage.addContent(headerText);
                heightRemaining -= 12;
            }
            firstSection = false;
            for (ResearchEntry entry : availableList) {
                // Append each entry from the list to the page, breaking where necessary
                tempPage.addContent(entry);
                heightRemaining -= 12;
                if (heightRemaining < 12 && !tempPage.getContents().isEmpty()) {
                    heightRemaining = 210;
                    this.pages.add(tempPage);
                    tempPage = new DisciplinePage(discipline);
                }
            }
        }
        if (!completeList.isEmpty()) {
            // Append the section header and spacer
            ITextComponent headerText = new TranslationTextComponent("primalmagic.grimoire.section_header.complete").applyTextStyle(TextFormatting.UNDERLINE);
            if (heightRemaining < 36 && !tempPage.getContents().isEmpty()) {
                // If there's not room for the spacer, the header, and a first entry, skip to the next page
                heightRemaining = 210;
                this.pages.add(tempPage);
                tempPage = new DisciplinePage(discipline);
                tempPage.addContent(headerText);
            } else {
                if (!firstSection && !tempPage.getContents().isEmpty()) {
                    tempPage.addContent(new StringTextComponent(""));
                    heightRemaining -= 12;
                }
                tempPage.addContent(headerText);
                heightRemaining -= 12;
            }
            firstSection = false;
            for (ResearchEntry entry : completeList) {
                // Append each entry from the list to the page, breaking where necessary
                tempPage.addContent(entry);
                heightRemaining -= 12;
                if (heightRemaining < 12 && !tempPage.getContents().isEmpty()) {
                    heightRemaining = 210;
                    this.pages.add(tempPage);
                    tempPage = new DisciplinePage(discipline);
                }
            }
        }
        if (!tempPage.getContents().isEmpty()) {
            this.pages.add(tempPage);
        }
    }
    
    protected void parseEntryPages(ResearchEntry entry) {
        if (entry == null || entry.getStages().isEmpty()) {
            return;
        }
        
        // Determine current research stage
        boolean complete = false;
        this.currentStageIndex = this.knowledge.getResearchStage(entry.getKey()) - 1; // remember, it's one-based
        if (this.currentStageIndex >= entry.getStages().size()) {
            this.currentStageIndex = entry.getStages().size() - 1;
            complete = true;
        }
        if (this.currentStageIndex < 0) {
            this.currentStageIndex = 0;
        }
        ResearchStage stage = entry.getStages().get(this.currentStageIndex);
        List<ResearchAddendum> addenda = complete ? entry.getAddenda() : new ArrayList<>();
        
        // TODO generate recipe lists from stage and addenda
        
        String rawText = (new TranslationTextComponent(stage.getTextTranslationKey())).getString();
        
        // Append unlocked addendum text
        int addendumCount = 0;
        for (ResearchAddendum addendum : addenda) {
            if (addendum.getRequiredResearch() != null && addendum.getRequiredResearch().isKnownByStrict(this.getMinecraft().player)) {
                ITextComponent headerText = new TranslationTextComponent("primalmagic.grimoire.addendum_header", ++addendumCount);
                ITextComponent addendumText = new TranslationTextComponent(addendum.getTextTranslationKey());
                rawText += ("<PAGE>" + headerText.getFormattedText() + "<BR>" + addendumText.getFormattedText());
            }
        }
        
        // Process text
        rawText = rawText.replaceAll("<BR>", "~B\n\n");
        rawText = rawText.replaceAll("<LINE>", "~L");
        rawText = rawText.replaceAll("<PAGE>", "~P");
        List<PageImage> images = new ArrayList<>();
        String[] imgSplit = rawText.split("<IMG>");
        for (String imgStr : imgSplit) {
            int index = imgStr.indexOf("</IMG>");
            if (index >= 0) {
                String cleanStr = imgStr.substring(0, index);
                PageImage newImage = PageImage.parse(cleanStr);
                if (newImage == null) {
                    rawText = rawText.replaceFirst(cleanStr, "\n");
                } else {
                    images.add(newImage);
                    rawText = rawText.replaceFirst(cleanStr, "~I");
                }
            }
        }
        rawText = rawText.replaceAll("<IMG>", "");
        rawText = rawText.replaceAll("</IMG>", "");
        
        // Split raw text into formattable sections
        List<String> firstPassText = new ArrayList<>();
        String[] pageTokens = rawText.split("~P");
        for (int i = 0; i < pageTokens.length; i++) {
            String[] lineTokens = pageTokens[i].split("~L");
            for (int j = 0; j < lineTokens.length; j++) {
                String[] imgTokens = lineTokens[j].split("~I");
                for (int k = 0; k < imgTokens.length; k++) {
                    firstPassText.add(imgTokens[k]);
                    if (k != imgTokens.length - 1) {
                        firstPassText.add("~I");
                    }
                }
                if (j != lineTokens.length - 1) {
                    firstPassText.add("~L");
                }
            }
            if (i != pageTokens.length - 1) {
                firstPassText.add("~P");
            }
        }
        
        // Format text by font
        int lineHeight = this.font.FONT_HEIGHT;
        List<String> parsedText = new ArrayList<>();
        for (String str : firstPassText) {
            parsedText.addAll(this.font.listFormattedStringToWidth(str, 140));
        }
        
        // First page has less available space to account for title
        int heightRemaining = 182;
        
        // Break parsed text into pages
        StagePage tempPage = new StagePage(stage, true);
        List<PageImage> tempImages = new ArrayList<>();
        for (String line : parsedText) {
            if (line.contains("~I")) {
                if (!images.isEmpty()) {
                    tempImages.add(images.remove(0));
                }
                line = "";
            }
            if (line.contains("~L")) {
                tempImages.add(IMAGE_LINE);
                line = "";
            }
            if (line.contains("~P")) {
                this.pages.add(tempPage);
                tempPage = new StagePage(stage);
                heightRemaining = 210;
                line = "";
            }
            if (!line.isEmpty()) {
                line = line.trim();
                tempPage.addElement(new PageString(line));
                heightRemaining -= lineHeight;
                if (line.endsWith("~B")) {
                    heightRemaining -= (int)(lineHeight * 0.66D);
                }
            }
            while (!tempImages.isEmpty() && (heightRemaining >= (tempImages.get(0).adjustedHeight + 2))) {
                heightRemaining -= (tempImages.get(0).adjustedHeight + 2);
                tempPage.addElement(tempImages.remove(0));
            }
            if ((heightRemaining < lineHeight) && !tempPage.getElements().isEmpty()) {
                heightRemaining = 210;
                this.pages.add(tempPage);
                tempPage = new StagePage(stage);
            }
        }
        if (!tempPage.getElements().isEmpty()) {
            this.pages.add(tempPage);
        }
        
        // Deal with any remaining images
        tempPage = new StagePage(stage);
        heightRemaining = 210;
        while (!tempImages.isEmpty()) {
            if (heightRemaining < (tempImages.get(0).adjustedHeight + 2)) {
                heightRemaining = 210;
                this.pages.add(tempPage);
                tempPage = new StagePage(stage);
            } else {
                heightRemaining -= (tempImages.get(0).adjustedHeight + 2);
                tempPage.addElement(tempImages.remove(0));
            }
        }
        if (!tempPage.getElements().isEmpty()) {
            this.pages.add(tempPage);
        }
        
        // Add requirements page if applicable
        if (!entry.getKey().isKnownByStrict(this.getMinecraft().player) && stage.hasPrerequisites()) {
            this.pages.add(new RequirementsPage(stage));
        }
    }
    
    public void nextPage() {
        if (this.currentPage < this.pages.size() - 2) {
            this.currentPage += 2;
            this.initButtons();
        }
    }
    
    public void prevPage() {
        if (this.currentPage >= 2) {
            this.currentPage -= 2;
            this.initButtons();
        }
    }
    
    public void goBack() {
        if (!HISTORY.isEmpty()) {
            Object lastTopic = HISTORY.remove(HISTORY.size() - 1);
            this.container.setTopic(lastTopic);
            this.getMinecraft().displayGuiScreen(new GrimoireScreen(this.container, this.playerInventory, this.title));
        }
    }
    
    protected void updateNavButtonVisibility() {
        this.prevPageButton.visible = (this.currentPage >= 2);
        this.nextPageButton.visible = (this.currentPage < this.pages.size() - 2);
        this.backButton.visible = !HISTORY.isEmpty();
    }
    
    @Override
    public void onClose() {
        HISTORY.clear();
        super.onClose();
    }
    
    @Override
    public boolean keyPressed(int p_keyPressed_1_, int p_keyPressed_2_, int p_keyPressed_3_) {
        if (p_keyPressed_1_ == GLFW.GLFW_KEY_ESCAPE) {
            HISTORY.clear();
        }
        return super.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_);
    }
}
