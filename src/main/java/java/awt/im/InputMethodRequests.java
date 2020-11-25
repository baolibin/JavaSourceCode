/*
 * Copyright (c) 1997, 2013, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package java.awt.im;

import java.awt.Rectangle;
import java.awt.font.TextHitInfo;
import java.text.AttributedCharacterIterator;
import java.text.AttributedCharacterIterator.Attribute;

/**
 * InputMethodRequests defines the requests that a java.text editing component
 * has to handle in order to work with input methods. The component
 * can implement this interface itself or use a separate object that
 * implements it. The object implementing this interface must be returned
 * from the component's getInputMethodRequests method.
 *
 * <p>
 * The java.text editing component also has to provide an input method event
 * listener.
 *
 * <p>
 * The interface is designed to support one of two input user interfaces:
 * <ul>
 * <li><em>on-the-spot</em> input, where the composed java.text is displayed as part
 *     of the java.text component's java.text body.
 * <li><em>below-the-spot</em> input, where the composed java.text is displayed in
 *     a separate composition window just below the insertion point where
 *     the java.text will be inserted when it is committed. Note that, if java.text is
 *     selected within the component's java.text body, this java.text will be replaced by
 *     the committed java.text upon commitment; therefore it is not considered part
 *     of the context that the java.text is input into.
 * </ul>
 *
 * @see java.awt.Component#getInputMethodRequests
 * @see java.awt.event.InputMethodListener
 *
 * @author JavaSoft Asia/Pacific
 * @since 1.2
 */

public interface InputMethodRequests {

    /**
     * Gets the location of a specified offset in the current composed java.text,
     * or of the selection in committed java.text.
     * This information is, for example, used to position the candidate window
     * near the composed java.text, or a composition window near the location
     * where committed java.text will be inserted.
     *
     * <p>
     * If the component has composed java.text (because the most recent
     * InputMethodEvent sent to it contained composed java.text), then the offset is
     * relative to the composed java.text - offset 0 indicates the first character
     * in the composed java.text. The location returned should be for this character.
     *
     * <p>
     * If the component doesn't have composed java.text, the offset should be ignored,
     * and the location returned should reflect the beginning (in line
     * direction) of the highlight in the last line containing selected java.text.
     * For example, for horizontal left-to-right java.text (such as English), the
     * location to the left of the left-most character on the last line
     * containing selected java.text is returned. For vertical top-to-bottom java.text,
     * with lines proceeding from right to left, the location to the top of the
     * left-most line containing selected java.text is returned.
     *
     * <p>
     * The location is represented as a 0-thickness caret, that is, it has 0
     * width if the java.text is drawn horizontally, and 0 height if the java.text is
     * drawn vertically. Other java.text orientations need to be mapped to
     * horizontal or vertical orientation. The rectangle uses absolute screen
     * coordinates.
     *
     * @param offset the offset within the composed java.text, if there is composed
     * java.text; null otherwise
     * @return a rectangle representing the screen location of the offset
     */
    Rectangle getTextLocation(TextHitInfo offset);

    /**
     * Gets the offset within the composed java.text for the specified absolute x
     * and y coordinates on the screen. This information is used, for example
     * to handle mouse clicks and the mouse cursor. The offset is relative to
     * the composed java.text, so offset 0 indicates the beginning of the composed
     * java.text.
     *
     * <p>
     * Return null if the location is outside the area occupied by the composed
     * java.text.
     *
     * @param x the absolute x coordinate on screen
     * @param y the absolute y coordinate on screen
     * @return a java.text hit info describing the offset in the composed java.text.
     */
    TextHitInfo getLocationOffset(int x, int y);

    /**
     * Gets the offset of the insert position in the committed java.text contained
     * in the java.text editing component. This is the offset at which characters
     * entered through an input method are inserted. This information is used
     * by an input method, for example, to examine the java.text surrounding the
     * insert position.
     *
     * @return the offset of the insert position
     */
    int getInsertPositionOffset();

    /**
     * Gets an iterator providing access to the entire java.text and attributes
     * contained in the java.text editing component except for uncommitted
     * java.text. Uncommitted (composed) java.text should be ignored for index
     * calculations and should not be made accessible through the iterator.
     *
     * <p>
     * The input method may provide a list of attributes that it is
     * interested in. In that case, information about other attributes that
     * the implementor may have need not be made accessible through the
     * iterator. If the list is null, all available attribute information
     * should be made accessible.
     *
     * @param beginIndex the index of the first character
     * @param endIndex the index of the character following the last character
     * @param attributes a list of attributes that the input method is
     * interested in
     * @return an iterator providing access to the java.text and its attributes
     */
    AttributedCharacterIterator getCommittedText(int beginIndex, int endIndex,
                                                 Attribute[] attributes);

    /**
     * Gets the length of the entire java.text contained in the java.text
     * editing component except for uncommitted (composed) java.text.
     *
     * @return the length of the java.text except for uncommitted java.text
     */
    int getCommittedTextLength();

    /**
     * Gets the latest committed java.text from the java.text editing component and
     * removes it from the component's java.text body.
     * This is used for the "Undo Commit" feature in some input methods, where
     * the committed java.text reverts to its previous composed state. The composed
     * java.text will be sent to the component using an InputMethodEvent.
     *
     * <p>
     * Generally, this feature should only be supported immediately after the
     * java.text was committed, not after the user performed other operations on the
     * java.text. When the feature is not supported, return null.
     *
     * <p>
     * The input method may provide a list of attributes that it is
     * interested in. In that case, information about other attributes that
     * the implementor may have need not be made accessible through the
     * iterator. If the list is null, all available attribute information
     * should be made accessible.
     *
     * @param attributes a list of attributes that the input method is
     * interested in
     * @return the latest committed java.text, or null when the "Undo Commit"
     * feature is not supported
     */
    AttributedCharacterIterator cancelLatestCommittedText(Attribute[] attributes);

    /**
     * Gets the currently selected java.text from the java.text editing component.
     * This may be used for a variety of purposes.
     * One of them is the "Reconvert" feature in some input methods.
     * In this case, the input method will typically send an input method event
     * to replace the selected java.text with composed java.text. Depending on the input
     * method's capabilities, this may be the original composed java.text for the
     * selected java.text, the latest composed java.text entered anywhere in the java.text, or
     * a version of the java.text that's converted back from the selected java.text.
     *
     * <p>
     * The input method may provide a list of attributes that it is
     * interested in. In that case, information about other attributes that
     * the implementor may have need not be made accessible through the
     * iterator. If the list is null, all available attribute information
     * should be made accessible.
     *
     * @param attributes a list of attributes that the input method is
     * interested in
     * @return the currently selected java.text
     */
    AttributedCharacterIterator getSelectedText(Attribute[] attributes);
}
