/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ Copyright 2021 Adobe
 ~
 ~ Licensed under the Apache License, Version 2.0 (the "License");
 ~ you may not use this file except in compliance with the License.
 ~ You may obtain a copy of the License at
 ~
 ~     http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ Unless required by applicable law or agreed to in writing, software
 ~ distributed under the License is distributed on an "AS IS" BASIS,
 ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ~ See the License for the specific language governing permissions and
 ~ limitations under the License.
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
package com.adobe.cq.wcm.core.components.internal.link;

import static com.adobe.cq.wcm.core.components.internal.link.LinkTestUtils.assertInvalidLink;
import static com.adobe.cq.wcm.core.components.internal.link.LinkTestUtils.assertValidLink;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import com.adobe.cq.wcm.core.components.commons.link.Link;
import com.day.cq.wcm.api.Page;

class LinkImplTest {

    private static final String URL = "/url.html";

    @Test
    void testValidLink() {
        Link link = new LinkImpl(URL);

        assertValidLink(link, URL);
        assertNull(link.getReference());
    }

    @Test
    void testValidLinkWithTarget() {
        Link link = new LinkImpl(URL, "_blank");

        assertValidLink(link, URL, "_blank");
        assertNull(link.getReference());
    }

    @Test
    void testValidLinkWithoutTarget() {
        Link link = new LinkImpl(URL, null);

        assertValidLink(link, URL, null);
        assertNull(link.getReference());
    }

    @Test
    void testValidLinkWithTargetAndTargetPage() {
        Page page = mock(Page.class);
        Link<Page> link = new LinkImpl(URL, "_blank", page);

        assertValidLink(link, URL, "_blank");
        assertSame(page, link.getReference());
    }

    @Test
    void testValidLinkWithTargetTargetPageAccessibilityLabelAndTitleAttribute() {
        Page page = mock(Page.class);
        Link link = new LinkImpl(URL, "_blank", page, "Url Label", "Url Title");

        assertValidLink(link, URL, "Url Label", "Url Title", "_blank");
        assertSame(page, link.getReference());
    }

    @Test
    void testValidLinkWithTargetPageAccessibilityLabelTitleAttributeAndWithoutTarget() {
        Page page = mock(Page.class);
        Link link = new LinkImpl(URL, null, page, "Url Label", "Url Title");

        assertValidLink(link, URL, "Url Label", "Url Title", null);
        assertSame(page, link.getReference());
    }

    @Test
    void testInvalidLink() {
        Link link = new LinkImpl(null, null, null);

        assertInvalidLink(link);
        assertNull(link.getReference());
    }
}
