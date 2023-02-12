import React, { useEffect } from "react";
import $ from "jquery";

const QuestTutorial = () => {
  useEffect(() => {
    const flipbook = $("#flipbook");
    flipbook.turn({
      width: 800,
      height: 600,
      autoCenter: true,
    });
  }, []);

  return (
    <div className="modalBody">
      <div id="flipbook">
        <div class="hard">Page1</div>
        <div>Page1</div>
        <div>Page1</div>
      </div>
    </div>
  );
};

export default QuestTutorial;
